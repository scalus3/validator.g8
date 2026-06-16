package validator

import org.scalatest.funsuite.AnyFunSuite
import scalus.compiler.compile
import scalus.uplc.builtin.ByteString.*
import scalus.uplc.builtin.Data.toData
import scalus.cardano.onchain.plutus.v1.PubKeyHash
import scalus.cardano.onchain.plutus.prelude.*
import scalus.testing.kit.ScalusTest

class $name;format="Camel"$Test extends AnyFunSuite with ScalusTest {

    private val owner =
        PubKeyHash(
          hex"1234567890abcdef1234567890abcdef1234567890abcdef12345678"
        )

    test("$name;format="Camel"$ succeeds when signed by the owner") {
        val context = makeSpendingScriptContext(
          datum = owner.toData,
          redeemer = BigInt(0).toData,
          signatories = List(owner)
        )
        // running spend validator
        val result = compile($name;format="Camel"$.validate).runScript(context)
        assert(result.isSuccess)
    }

    test("$name;format="Camel"$ fails when the owner did not sign") {
        val context = makeSpendingScriptContext(
          datum = owner.toData,
          redeemer = BigInt(0).toData,
          signatories = List.empty
        )

        val result = compile($name;format="Camel"$.validate).runScript(context)
        assert(!result.isSuccess)
    }
}
