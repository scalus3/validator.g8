package validator

import scalus.*
import scalus.builtin.Data
import scalus.ledger.api.v3.*
import scalus.prelude.*
import scalus.prelude.Option.*
import scalus.prelude.Prelude.*

@Compile
object $name;format="Camel"$ extends Validator:
    override inline def spend(
         datum: Option[Data],
         redeemer: Data,
         tx: TxInfo,
         ownRef: TxOutRef
         ): Unit = {
        fail("Empty Validator.spend")
    }

    override inline def mint(
        redeemer: Data,
        policyId: PolicyId,
        tx: TxInfo
    ): Unit = {
        fail("Empty Validator.mint")
    }

    override inline def reward(
        redeemer: Data,
        stakingKey: Credential,
        tx: TxInfo
    ): Unit = {
        fail("Empty Validator.reward")
    }

    override inline def certify(
        redeemer: Data,
        cert: TxCert,
        tx: TxInfo
    ): Unit = {
        fail("Empty Validator.certify")
    }

    override inline def vote(
        redeemer: Data,
        voter: Voter,
        tx: TxInfo
    ): Unit = {
        fail("Empty Validator.vote")
    }

    override inline def propose(
        proposalProcedure: ProposalProcedure,
        tx: TxInfo
    ): Unit = {
        fail("Empty Validator.propose")
    }

