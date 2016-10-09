# Week 7: Adversarial Search and Decision Theory

## Adversarial World
- Making decisions requires respecting your opponents
- Modify algorithm based on actions taken by opponents

## Game Tree
- Similar to AND-OR tree
- OR level: The agent's move (Maximize value)
- AND level: The opponent's move (Minimize value)

## Minmax Algorithm
- Using the current state as the initial search, build the game tree to the maximal depth __h__ (called horizon) feasible within the time limit
- Evaluate the states of the leaf nodes. Use a heuristic as an evaluation function to estimate how favorable a state is.
- Back up the results from the leaves to the root and pick the best action assuming the worst case from MIN
    - At each non-leaf node N, the backup up value is the value of the best state that MAX can reach at depth __h__ if MIN plays well (by the same criterion as MAX applies to itself)
    - Same criterion: Same evaluation function

Psuedo-code:
```
1. Expand the game tree from the current state (where it is MAX's turn to play) to depth h
2. Compute the evaluation functin at every leaf of the tree
3. Back up the values from the leaves to the root of the tree as follows
    a. A MAX node gets the maximum of the evaluation of its successors
    b. A MIN node gets the minimum of the evaluation of its successors
4. Select the move toward a MIN node that has the largest backed up value
```

## Improving Minmax: Alpha Beta Pruning

- __a__: Best already explored option along the path to the root maximizer
- __b__: Best already explored option along path to the root minimizer
- Explore the game tree to depth _h_ in depth-first manner
- Back _a_ and _b_ values whenever possible
- Prune branches that can't lead to changing the final decision

### How much do we gain?
- Assume a game tree of uniform branching factor __b__
- __h__: Depth of the tree
- Minmax examinesO(b^h) nodes, so does alpha-beta in the worst case

## Decision Theory
- A framework for an agent to choose the "best" decision in a non-deterministic environment
- Can be thought of as a calculus for decision making
- A good basis for building intelligent (rational) agents
- Using this framework, a problem is represented as:
    - Set of states (primitive outcomes)
    - Preference, which outcome is preferred
    - Lotteries, A set of possible outcomes with their probability of occurring, represents the probability of non-determinism of an action

### Maximum Expected Utility (MEU)
- Main problem: What does "best" mean
- Utility: A number that assigns the desirability of a state
- MEU is the commonly used definition of "best" decision
- Idea:
    - Assigns the utility function to each outcome (state) to represent the agent's preference
    - "Best" decision maximizes the expected utility of the outcomes

### Example: Buying a car
- Goal of buying the car: To gain profit from reselling it
- Costs $1000
- Can sell the car for $1100 -> $100 profit
- Every car is either good or bad
    - Costs $40 to repair a good car
    - Costs $200 to repair a bad car
- 20% of cars are bad

State space: {good car, bad car}

Utility function:
    - `U(good car) = 1100 - 1000 - 40 = 60`
    - `U(bad car) = 1100 - 1000 - 200 = -100`
    
Lottery: [0.8 for good car, 0.2 for bad car]

Expected Utility if we buy the car:
    - `P(good car) * U(good car) + P(bad car) * U(bad car)`
    - `0.8 * 60 + 0.2 * -100 = 28`
    - Higher than not buying the car, therefore buy

### Preference of Rational Agent: Axioms of Utility Theory
1. Orderability
    - Given any two states A and B, the following must be true
    - `A > B OR B > A OR A ~= B`
    - The agent must decide if it prefers A or B or indifferent to the two states. It cannot avoid making decisions

2. Transitivity
    - Given any three states A, B and C, the following must be true
    - `IF (A > B AND B > C) THEN A > C`

3. Continuity
    - If some state B is between state A and C in terms of preference, then there must be a probability p such that the agent will be indifferent between getting B and getting A with probability p or getting C with probability (1 - p)

4. Substitutability
    - If an agent is indifferent about two primitive outcomes A and B, then the agent is indifferent about two more complex outcomes that are the same except B is substituted with A or A is substituted with B

5. Monotonicity
    - Suppose there are 2 lotteries with the same outcomes, A & B. If the agent prefers A than B, then the agent must prefer the lottery with a higher probablity on A

6. Decomposability
    - Compound lotteries can be simplified into simpler ones using probability theory


### Main Theorem
- If preferences satisfy all these six axioms, then there exists a real value function U such that if A is preferred than B, the U(A) > U(B); and if both are equally preferred then U(A) = U(B)
- Thus we can work directly with the utility rather than preferences
- The utility of a lottery = expected utility of outcomes

### Utility of Money
- Which one do you prefer?
    - A: A sure gain of $240
    - B: A 25% chance of winning $1000 and a 75% of getting nothing

- Which one do prefer?
    - C: A sure loss of $250
    - D: A 25% chance of losing $1000 and a 75% chance of losing nothing

- Utility of money is not always the same as the nominal value of money
    - Can be anything, classified as 3 types of risks:
        - Neutral
        - Risk Averse
        - Risk Seeker
    - If your preference satisfies the axioms, then there is at least one utility function that reflects that axiom, decision theory applies and MEU gives the best choice

### Risk Neutral
- Risk Neutral: The utility of money is the same as the nominal amount of money
- Utility(lottery) = expected utility of the nominal amount of money

- Which one do you prefer? __B__
    - A: A sure gain of $240
    - B: A 25% chance of winning $1000 and a 75% of getting nothing

- Which one do prefer? __Equal Preference__
    - C: A sure loss of $250
    - D: A 25% chance of losing $1000 and a 75% chance of losing nothing

### Risk Averse
- Risk Averse: Prefers smaller amount of money for sure, than lotteries with higher expected amount of money
- Which one do you prefer? __A__
    - A: A sure gain of $240
    - B: A 25% chance of winning $1000 and a 75% of getting nothing
    - `U(A) = U(240); U(B) = 0.25 * U(1000) + 0.75 * U(0)`
    - `U(A) > U(B)`

- Utility function can still hold (as long as preferences satisfy the six axioms), but the utility function is not the same as the nominal amount of the money

### Risk Seeker
- Risk Seeker: Prefers the uncertain than something sure
    - Usually desperate scenarios (e.g. loss)
    - Or a (compulsive) gambler

- Which one do prefer? __D__
    - C: A sure loss of $250
    - D: A 25% chance of losing $1000 and a 75% chance of losing nothing
    - `U(C) = U(-250); U(D) = 0.25 * U(-1000) + 0.75 * U(0)
    - `U(C) < U(D)`

### St. Petersburg Paradox
- Flip a coin
    - Each flip costs
    - Heads -> I'll pay you $2
    - Tails -> Flip again
        - Heads -> I'll pay you $8
        - Tails -> Flip again

Paradox: Expected value = infinite, but most people don't want to pay > $4 to play it

### Utility of Money
- Is decision theory useless here?
    - It can still be used, as long as the 6 axioms for preferences are satisfied
    - It's just that the utility function is not equals to the nominal value of the money
    - Need to find the right utility function

### Decision Tree Representation
- Similar to a game tree and AND/OR tree
- OR/max -> Choice: Agent makes the choice
- AND/min -> Lottery: Represents the lottery that corresponds to the outcome of the decision made at its parent's node
    - Often called chance node
    - Computes expected value

### Buying a used car
- Goal of buying the car: To gain profit from reselling it
- Costs $1000
- Can sell the car for $1100 -> $100 profit
- Every car is either good or bar
    - Costs $40 to repair a good car
    - Costs $200 to repair a bad car
- 20% of cars are bad
- Should we buy the car?

### Guarantee
- Costs $60
- If repairs <= $100, covers 50% of repair costs
- If repairs > $100, covers all

### Inspection
- Inspection checks if a car is good or bad
- Costs $9
- Not perfect:
    - P(pass | good) = 0.9, P(fail | good) = 0.1
    - P(pass | bad) = 0.4, P(fail | bad) = 0.6
