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

## Evaluation Function
- Need a heuristic to estimate how favorable is a game state for the agent (MAX)
    - Usually called the evaluation function e: S -> R
    - e(s) > 0: s is favorable to MAX (the larger the better)
    - e(s) < 0: s is favorable to MIN
    - e(s) = s: s is neutral

## Construction of an Evaluation Function
- Usually weighted sum of "features"
- Features may include
    - Number of pieces of each type
    - Number of possible moves
    - Number of squares controlled

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
