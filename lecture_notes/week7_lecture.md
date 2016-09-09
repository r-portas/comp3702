# Week 7: Adversarial Search and Decision Theory

## Adversarial World
- Making decisions requires respecting your opponents
- Modify algorithm based on actions taken by opponents

## Game Tree
- Similar to AND-OR tree
- OR level: The agent's move (Maximize value)
- AND level: The opponent's move (Minimize value)

## Minmax Algorithm
- Using the current state as the initial search, build the game tree to the maximal depth _h_ (called horizon) feasible within the time limit
- Evaluate the states of the leaf nodes. Use a heuristic as an evaluation function to estimate how favorable a state is.
- Back up the results from the leaves to the root and pick the best action assuming the worst case from MIN
    - At each non-leaf node N, the backup up value is the value of the best state that MAX can reach at depth _h_ if MIN plays well (by the same criterion as MAX applies to itself)
    - Same criterion: Same evaluation function

## Evaluation Function
- Need a heuristic to estimate how favorable is a game state for the agent (MAX)
    - Usually called the evaluation function e: S -> R
    - e(s) > 0: s is favorable to MAX (the larger the better)
    - e(s) < 0: s is favorable to MIN
    - e(s) = s: s is neutral

## Construction of an Evaluation Function


## Pruning
