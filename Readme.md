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

## Project layout

Both tools share the same flat source layout: sources live at the project root and test
sources use the `.test.scala` suffix.

- `$name;format="Camel"$.scala` — the on-chain validator
- `$name;format="Camel"$.test.scala` — tests using `scalus-testkit`'s `ScalusTest`
- `project.scala` — Scala CLI build configuration (Scala version, Scalus compiler plugin, deps)
- `build.sbt` — sbt build configuration (same versions and deps)

## Learn more

- Scalus documentation: https://scalus.org
- Examples: https://github.com/nau/scalus/tree/master/scalus-examples
