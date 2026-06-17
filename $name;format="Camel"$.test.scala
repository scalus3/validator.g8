package validator

import org.scalatest.funsuite.AnyFunSuite
import scalus.compiler.Options
import scalus.uplc.PlutusV3
import scalus.uplc.builtin.Data.toData
import scalus.cardano.ledger.*
import scalus.cardano.node.Emulator
import scalus.cardano.txbuilder.RedeemerPurpose.ForSpend
import scalus.cardano.txbuilder.{TxBuilder, txBuilder}
import scalus.cardano.onchain.plutus.v1.PubKeyHash
import scalus.testing.kit.Party.{Alice, Bob}
import scalus.testing.kit.{ScalusTest, TestUtil}
import scalus.testing.kit.TestUtil.getScriptContextV3
import scalus.uplc.eval.{ProfileFormatter, Result}
import scalus.utils.await

class $name;format="Camel"$Test extends AnyFunSuite, ScalusTest {

    private given Options = Options.default
    private given env: CardanoInfo = TestUtil.testEnvironment

    private val contract = PlutusV3.compile($name;format="Camel"$.validate)
    private val scriptAddress = contract.address(env.network)

    // Enabled by `sbt profile` (or SCALUS_PROFILE=1); writes target/profile.html.
    private val profilingEnabled =
        sys.props.get("scalus.profile").contains("true") ||
            sys.env.get("SCALUS_PROFILE").contains("1")

    /** Locks a UTxO at the contract owned by Alice, then builds a draft
      * spending transaction signed by `signer` and evaluates the validator on
      * its context.
      */
    private def spendSignedBy(signer: AddrKeyHash): Result = {
        val provider = Emulator.withAddresses(Seq(Alice.address))

        val fundingUtxos =
            provider.findUtxos(Alice.address).await().toOption.get
        val lockTx = TxBuilder(env)
            .payTo(scriptAddress, Value.ada(10), PubKeyHash(Alice.addrKeyHash))
            .complete(availableUtxos = fundingUtxos, sponsor = Alice.address)
            .sign(Alice.signer)
            .transaction
        assert(provider.submit(lockTx).await().isRight)
        val lockedUtxo = Utxo(lockTx.utxos.find { case (_, out) =>
            out.address == scriptAddress
        }.get)

        val scriptContext = txBuilder
            .spend(lockedUtxo, BigInt(0), contract)
            .requireSignature(signer)
            .payTo(Alice.address, lockedUtxo.output.value)
            .draft
            .getScriptContextV3(provider.utxos, ForSpend(lockedUtxo.input))

        val result = contract.program.runWithDebug(scriptContext)
        if profilingEnabled && result.isSuccess then
            contract.program.runWithProfile(scriptContext).profile.foreach {
                p =>
                    ProfileFormatter.writeHtml(
                      p,
                      "target/profile.html",
                      title = "$name;format="Camel"$"
                    )
                    info("Wrote profile to target/profile.html")
            }
        result
    }

    test("$name;format="Camel"$ succeeds when signed by the owner") {
        assert(spendSignedBy(Alice.addrKeyHash).isSuccess)
    }

    test("$name;format="Camel"$ fails when the owner did not sign") {
        assert(!spendSignedBy(Bob.addrKeyHash).isSuccess)
    }
}
