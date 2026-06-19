# Scalus $name;format="Camel"$ Smart Contract

A [Scalus](https://scalus.org) smart contract starter generated from the `validator.g8` template.

The contract is a Plutus V3 spending validator (`$name;format="Camel"$.scala`) that only allows the
locked UTxO to be spent when the transaction is signed by the owner stored in the datum. Replace its
body with your own logic.

## Prerequisites

- A JDK (17+)
- [Scala CLI](https://scala-cli.virtuslab.org/) and/or [sbt](https://www.scala-sbt.org/)

## Build & test

The project works with both Scala CLI and sbt.

With Scala CLI:

```sh
scala-cli test .
scala-cli compile .
scala-cli fmt .
```

With sbt:

```sh
sbt test
sbt compile
```

### Test backends

`$name;format="Camel"$Integration.test.scala` runs against a backend selected by the
`SCALUS_TEST_ENV` environment variable:

```sh
sbt test                       # in-memory emulator (default, fast)
SCALUS_TEST_ENV=yaci sbt test  # a local Yaci DevKit node (requires a running Docker daemon)
```

The same works with Scala CLI, e.g. `SCALUS_TEST_ENV=yaci scala-cli test .`.

## Project layout

Both tools share the same flat source layout: sources live at the project root and test
sources use the `.test.scala` suffix.

- `$name;format="Camel"$.scala` — the on-chain validator
- `$name;format="Camel"$.test.scala` — unit tests using `scalus-testkit`'s `ScalusTest`
- `$name;format="Camel"$Integration.test.scala` — submit-based tests (emulator / Yaci DevKit)
- `$name;format="Camel"$Contract.scala` — the `Contract` (compiled script + CIP-57 blueprint)
- `project.scala` — Scala CLI build configuration (Scala version, Scalus compiler plugin, deps)
- `build.sbt` — sbt build configuration (same versions and deps)

## Blueprint & deploy

The sbt build enables the [`scalus-sbt-plugin`](https://github.com/nau/scalus), which adds tasks
backed by the `Contract` in `$name;format="Camel"$Contract.scala`:

```sh
# Generate a CIP-57 blueprint under target/.../META-INF/scalus/blueprints/
sbt blueprint

# Deploy the validator as a reference-script UTxO (needs a Blockfrost key and a funded wallet)
export BLOCKFROST_API_KEY=...        # or pass --blockfrost-key
export CARDANO_MNEMONIC="word1 ..."  # or pass --mnemonic
sbt "deploy $name;format="Camel"$Contract --network preview"
```

## Profiling

Set `SCALUS_PROFILE=1` to run the tests with on-chain execution profiling enabled. It writes an
interactive HTML profile (CPU/memory per source line) to `target/profile.html`:

```sh
SCALUS_PROFILE=1 sbt test
```

The same works with Scala CLI, e.g. `SCALUS_PROFILE=1 scala-cli test .`.

## Learn more

- Scalus documentation: https://scalus.org
- Examples: https://github.com/nau/scalus/tree/master/scalus-examples
