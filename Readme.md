# Scalus $name;format="Camel"$ Smart Contract

A [Scalus](https://scalus.org) smart contract starter generated from the `validator.g8` template.

The contract is a Plutus V3 spending validator (`$name;format="Camel"$.scala`) that only allows the
locked UTxO to be spent when the transaction is signed by the owner stored in the datum. Replace its
body with your own logic.

## Prerequisites

- A JDK (17+)
- [Scala CLI](https://scala-cli.virtuslab.org/)

## Build & test

```sh
scala-cli test .
```

```sh
scala-cli compile .
```

Format the sources with:

```sh
scala-cli fmt .
```

## Project layout

- `$name;format="Camel"$.scala` — the on-chain validator
- `$name;format="Camel"$.test.scala` — tests using `scalus-testkit`'s `ScalusTest`
- `project.scala` — Scala CLI build configuration (Scala version, Scalus compiler plugin, deps)

## Learn more

- Scalus documentation: https://scalus.org
- Examples: https://github.com/nau/scalus/tree/master/scalus-examples
