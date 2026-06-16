package validator

import org.scalatest.funsuite.AnyFunSuite
import scalus.compiler.Options
import scalus.uplc.PlutusV3
import scalus.uplc.builtin.ByteString.*
import scalus.uplc.builtin.Data.toData
import scalus.cardano.onchain.plutus.v1.PubKeyHash
import scalus.cardano.onchain.plutus.prelude.*
import scalus.testing.kit.ScalusTest

class $name;format="Camel"$Test extends AnyFunSuite with ScalusTest {

    private given Options = Options.default

    private val owner =
        PubKeyHash(
          hex"1234567890abcdef1234567890abcdef1234567890abcdef12345678"
        )

    private val compiled = PlutusV3.compile($name;format="Camel"$.validate)

    test("$name;format="Camel"$ succeeds when signed by the owner") {
        val context = makeSpendingScriptContext(
          datum = owner.toData,
          redeemer = BigInt(0).toData,
          signatories = List(owner)
        )

        val result = compiled.program.runWithDebug(context)
        assert(result.isSuccess)
    }

    test("$name;format="Camel"$ fails when the owner did not sign") {
        val context = makeSpendingScriptContext(
          datum = owner.toData,
          redeemer = BigInt(0).toData,
          signatories = List.empty
        )

        val result = compiled.program.runWithDebug(context)
        assert(!result.isSuccess)
    }
}
