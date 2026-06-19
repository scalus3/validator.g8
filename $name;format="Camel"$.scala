package validator

import scalus.compiler.Compile
import scalus.uplc.builtin.Data
import scalus.cardano.onchain.plutus.v1.PolicyId
import scalus.cardano.onchain.plutus.v3.*
import scalus.cardano.onchain.plutus.prelude.*

/** A Plutus V3 validator scaffold.
  *
  * Each script purpose is stubbed and fails until you implement it. Fill in the
  * bodies you need and remove the ones you don't (any purpose left as `fail`
  * simply rejects).
  */
@Compile
object $name;format="Camel"$ extends Validator {
    inline override def spend(
        datum: Option[Data],
        redeemer: Data,
        tx: TxInfo,
        ownRef: TxOutRef
    ): Unit = fail("Empty Validator.spend")

    inline override def mint(
        redeemer: Data,
        policyId: PolicyId,
        tx: TxInfo
    ): Unit = fail("Empty Validator.mint")

    inline override def reward(
        redeemer: Data,
        stakingKey: Credential,
        tx: TxInfo
    ): Unit = fail("Empty Validator.reward")

    inline override def certify(
        redeemer: Data,
        cert: TxCert,
        tx: TxInfo
    ): Unit = fail("Empty Validator.certify")

    inline override def vote(
        redeemer: Data,
        voter: Voter,
        tx: TxInfo
    ): Unit = fail("Empty Validator.vote")

    inline override def propose(
        proposalProcedure: ProposalProcedure,
        tx: TxInfo
    ): Unit = fail("Empty Validator.propose")
}
