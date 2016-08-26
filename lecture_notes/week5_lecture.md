# Week 5: Logic

## What is logic
- A formal language to represent sets of states
  - A convenient abstraction for dealing with many states
  - Recall in PRM: We can view a vertex in a roadmap to represent a set of 'nearby' states
  - Regardless of wheter there's a natural notion of 'near' or not (i.e. not in metric space), we can use logic to group different states together

### Logic Formal Language
- Defined by two things: Syntax and Semantics
- Syntax: What expressions are legal
- Semantics: Meaning of the legal expression

### Many types of logic
- Propositional logic
- Predicate / first order logic
- High order logic

### Propositional Logic
- Atomic Sequence
  - An expression which is either true or false
  - Often represented with a symbol called propositional variable (e.g., P, Q)
- Syntax:
  - __~__ negation
  - __^__ and
  - __v__ or
  - __->__ implication
  - __<->__ biconditional

- Semantics:
  - Interpretation: Assignment of truth values to propositional variables
  - A model of a sentence: An interpretation that makes the sentence to be true
  - A sentence A entails another sentence B iff every model of A is also a model of B (A -> B is true)

- Entailment
  - Relation between sentences are based on relation between their intrepretations
- Terminology
  - A sentence is valid: Its truth value is T for all possible interpretations
  - A sentence is satisfiable: Its truth value is T for at least one of the possible intrepretations
  - A sentence is unsatisfiable: Its truth value is F for all possible intrepretations
  - For propositional logic, we can always decide if a sentence is valid/satisfiabled/unsatifiable in finite time (decidable)
