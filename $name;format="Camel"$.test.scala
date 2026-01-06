package validator

import org.scalatest.funsuite.AnyFunSuite
import scalus.*
import scalus.Compiler.compile
import scalus.builtin.ByteString.*
import scalus.builtin.Data
import scalus.builtin.Data.toData
import scalus.ledger.api.v1.PubKeyHash
import scalus.ledger.api.v3.*
import scalus.ledger.api.v3.given
import scalus.prelude.*
import scalus.testing.kit.ScalusTest

import scala.language.implicitConversions

class $name;format="Camel"$Test extends AnyFunSuite with ScalusTest {

    test("$name;format="Camel"$ should work correctly") {

        // building htlc-like script context
        val inputs = List(
          makePubKeyHashInput(hex"1234567890abcdef1234567890abcdef1234567890abcdef12345678", 0),
        )
        val context = ScriptContext(
          txInfo = TxInfo(inputs = inputs, id = random[TxId]),
          scriptInfo = ScriptInfo.SpendingScript(txOutRef = inputs.head.outRef)
        )
        // running spend validator
        val result = compile($name;format="Camel"$.validate).runScript(context)
        // should fail, since spend logic isn't implemented
        assert(result.isSuccess)
    }
}