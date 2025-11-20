package validator

import scalus.*
import scalus.builtin.Data
import scalus.ledger.api.v3.*
import scalus.prelude.*

@Compile
object $name;format="Camel"$ extends Validator {
    inline override def spend(
         datum: Option[Data],
         redeemer: Data,
         tx: TxInfo,
         ownRef: TxOutRef
    ): Unit = {
        fail("Implement spend logic")
    }

    inline override def mint(
        redeemer: Data,
        policyId: PolicyId,
        tx: TxInfo
    ): Unit = {
        fail("Implement minting logic")
    }

    inline override def reward(
        redeemer: Data,
        stakingKey: Credential,
        tx: TxInfo
    ): Unit = {
        fail("Implement withdraw rewards logic")
    }

    inline override def certify(
        redeemer: Data,
        cert: TxCert,
        tx: TxInfo
    ): Unit = {
        fail("Implement publish certificates logic")
    }

    inline override def vote(
        redeemer: Data,
        voter: Voter,
        tx: TxInfo
    ): Unit = {
        fail("Implement voting logic")
    }

    inline override def propose(
        proposalProcedure: ProposalProcedure,
        tx: TxInfo
    ): Unit = {
        fail("Implement propose logic")
    }
}