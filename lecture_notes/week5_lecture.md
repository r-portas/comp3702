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

## How logic is used in AI

- Formulate information as propositional sentences
  - Create a knowledge base (KB)
  - KB: Set of sentences, such that KB is false in models that contradict what the agent knows

## Types of Problems

### Validity
- Is the sentence valid?
  - The sentence is true under all interpretations

- Model checking
  - Sound: The result is correct
  - Complete: It always gives an answer
  - Complexity:
    - Time: O(2^n)
    - Space: O(n)
    - n is the number of propositional variables

- Theorem Proving: Natural deduction using search
  - State space
    - All possible set of sentences
  - Action space
    - All inference rules
  - World dynamic
    - Apply the inference rule to all sentences that match the above the line part of the inference rule. Become the sentence that lies below the line of the inference rule
  - Initial state
    - Initial knowledge base
  - Goal state
    - The state contains the sentence we are trying to prove
   
  This is sound but may not complete. It depends on whether we can provide a complete list of inference rules.

## Theorem Proving - Natural Deduction using Search

- __State space__: All possible set of sentences
- __Action space__: All inference rules
- __World dynamics__: Apply the inference rule to all sentences that match the above the line part of the inference rule. Become the sentence that lies below the line of the inference rule.
- __Initial state__: Initial knowledge base
- __Goal state__: The state contains the sentence we're trying to prove

_Notes_: Natural deduction is sound but may not be complete, depends on wwhether we can provide a complete list of inference rules. When we can, the branching factor can be very high.

## Conjunctive Normal Form (CNF)
- Conjunctions of disjunctions
- Example: `(~A | B) & (C | D) & (E | F)`
- Terminologies
    - Clause: A disjunction of literals, e.g. `(~A | B)`
    - Literals: Variables or the negation of variables, e.g. `~A` and `B`
- Three steps to converting propositional logic to CNF
    - Eliminate arrows using definitions
    - Drive in negations using De Morgan's laws
    - Distribute OR over AND
- Theorem Proving
    - Three steps
        - Convert all sentences into CNF
        - Negate the desired conclusion
        - Apply resolution rule until
            - Derive false (a contradiction)
            - Can't apply the rule anymore

## Satisfiability Problems
- Is a given sentence satisfiable
    - There is at least one interpretation that makes the sentence to be True
- Various applications
    - Many problems can be phrased as a list of constraints, and a solution is an assignment that satisfies all constraints
- Satisfiability for propositional logic is a particular type of Constraint Satisfication Problem (CSP)

## Model Checking for Satisfiability Problem (SAT Solvers)
- Assign and simplify strategy
     - Consider a search tree where at each level we consider the possible assignments to one variable, say V
     - On one branch, we assume V is False and on the other we assume True
     - Given an assignment for a variable, we can simplify the sentence and then repeat the process for another variable
- Random assignment of all variables in the sentence

## Davis Putnam Logeman Loveland (DPLL)
- DPLL (Sentence S)
    - If S is empty, return True
    - If S has an empty clause, return False
    - IF S has a unit clause U, return DPLL(S(U))
        - Unit clause: Consists of only 1 literal
        - S(U) means a simplified S after a value is assigned to U
    - If S has a pure clause U, DPLL(S(U))
        - Pure clause: Appear as positive only or negative only in S
    - Pick a variable v
        - If DPLL(S(v)) then return true, else return DPLL(S(~v))
        - Heuristic to pick the variable: Max #occurences, Min size clauses

- Sound
- Complete
- Speed and memory consumption depend a lot on:
    - Which symbol is being assigned first
    - Which assignment is being followed first
    - A lot of methods have been proposed

## GSAT
- GSAT(Sentence S)
    - Loop n times
        - Randomly choose assignment for all variables, say A
        - Loop m times
            - Flip the variable that results in lowest cost, Cost(assignment) = number of unsatisfied clauses
            - Return True if cost is zero

- Sound but not complete
- Cannot be used to generate all satisfiable assignment

## DPLL vs GSAT
- Weakly constrained problems (large proportion of the assignments is satisfiable)
    - Easy for DPLL, Easy for GSAT
- Highly constrained problems (very few satisfiable assignments, sometimes only 1)
    - Easy for DPLL, Hard for GSAT
- Problems in the middle
    - Hard for DPLL, Hard for GSAT


