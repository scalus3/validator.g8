package validator

import org.scalatest.funsuite.AnyFunSuite
import scalus.*
import scalus.Compiler.compile
import scalus.builtin.ByteString.*
import scalus.builtin.Data
import scalus.builtin.Data.toData
import scalus.builtin.{ByteString, Data}
import scalus.ledger.api.v1.PubKeyHash
import scalus.ledger.api.v3.*
import scalus.ledger.api.v3.given
import scalus.prelude.*
import scalus.testkit.ScalusTest
import scalus.uplc.*
import scalus.uplc.eval.*

import scala.language.implicitConversions
import scala.math.Ordering.Implicits.*

class $name;format="Camel"$Test extends FunSuite, ScalusTest {

    test("$name;format="Camel"$ should work correctly") {

        val inputs = List(
          makePubKeyHashInput(hex"1234567890abcdef1234567890abcdef1234567890abcdef12345678", 0),
        )
        val context = ScriptContext(
          txInfo = TxInfo(inputs = inputs, id = random[TxId]),
          scriptInfo = ScriptInfo.SpendingScript(txOutRef = inputs.head.outRef)
        )

        val result = compile($name;format="Camel"$.validate).runScript(context)

        assert(result.isSuccess)
    }
}
