package validator

import scalus.compiler.Compile
import scalus.uplc.builtin.Data
import scalus.cardano.onchain.plutus.v3.*
import scalus.cardano.onchain.plutus.prelude.*

/** A minimal Plutus V3 spending validator.
  *
  * It allows the locked UTxO to be spent only when the transaction is signed by
  * the owner whose public key hash is stored in the datum. Replace this body
  * with your own contract logic.
  *
  * A `Validator` can also implement `mint`, `reward`, `certify`, `vote` and
  * `propose` for the other script purposes; any purpose you do not override
  * fails by default.
  */
@Compile
object $name;format="Camel"$ extends Validator {
    inline override def spend(
        datum: Option[Data],
        redeemer: Data,
        tx: TxInfo,
        ownRef: TxOutRef
    ): Unit = {
        val owner = datum.getOrFail("Datum not found").to[PubKeyHash]
        require(tx.isSignedBy(owner), "Must be signed by the owner")
    }
}
