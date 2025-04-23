package validator

import scalus.*
import scalus.builtin.Data
import scalus.builtin.FromDataInstances.given
import scalus.ledger.api.v1.FromDataInstances.given
import scalus.ledger.api.v3.*
import scalus.ledger.api.v3.FromDataInstances.given
import scalus.prelude.*
import scalus.prelude.Option.*
import scalus.prelude.Prelude.*

@Compile
object $name;format="Camel"$ extends Validator:
    override def spend(
        datum: Option[Data],
        redeemer: Data,
        tx: TxInfo,
        ownRef: TxOutRef
    ): Unit = {
        fail("Empty Validator.spend")
    }

    override def mint(
        redeemer: Data,
        currencySymbol: CurrencySymbol,
        tx: TxInfo
    ): Unit = {
        fail("Empty Validator.mint")
    }

    override def reward(
        stakingKey: Credential,
        tx: TxInfo
    ): Unit = {
        fail("Empty Validator.reward")
    }

    override def certify(
        cert: TxCert,
        tx: TxInfo
    ): Unit = {
        fail("Empty Validator.certify")
    }

    override def vote(
        redeemer: Data,
        voter: Voter,
        tx: TxInfo
    ): Unit = {
        fail("Empty Validator.vote")
    }

    override def propose(
        proposalProcedure: ProposalProcedure,
        tx: TxInfo
    ): Unit = {
        fail("Empty Validator.propose")
    }

