# Week 6: 

## Causes of uncertainty
- Control error or disturbances from external forces
    - Effect of performing an action is non-deterministic
- Errors in sensing & in processing of sensing data
    - Imperfect observation about the world (partially observable)

### Modelling Error
- Lazy
    - Rolling a dice in a casino. Depends on wind direction from air conditioning, number of people around the table
- Reduce computational complexity
    - Eliminate variables that will not affect the solution significantly
- Accidental error

### Abstraction that may lead to modelling error
- The actual possible states are often too large
- Simplify, so it's solvable by current computing power
- In general simplification means clustering several actual states together and assume all actual state in the same cluster are all the same
    - Meaning a state in our model corresponds to a set of actual states that are not differentiable by the program
- Similarly with action space
    - The effect of performing an action becomes non deterministic
- Usually bounded with uncertainty

## Making Decisions
- We want to find a plan that works regardless of what outcomes actually occur
- Can no longer rely on a sequence of actions
- Need a conditional plan. The action to perform depends on the outcome of previous action
    - Need a different type of tree data structure

## AND-OR Search
- A tree with interleaving AND and OR levels
- At each node of an OR level, branching is introduced by the agent's own choice
- At each node of an AND level, branching is introduced by the environment

## Example: Slippery vacuum robot
- States: Conjunctions of
    - Robot in R1, Robot in R2
    - R1 clean, R2 clean
- Action:
    - Left, Right, Suck(R1), Suck(R2)
- World Dynamics:
    - Non deterministic
- Initial State:
    - Robot in R1 & R1 is clean & R2 is dirty
- Goal State:
    - R1 is clean & R2 is clean

## AND-OR search tree
- Solution is a sub-tree that:
    - Has a goal node at every leaf
    - Specifies one action at each node of an OR level
    - Include every outcome branch at each node of an AND level
- An leaf state node is _solved_ if its a goal state 
- An leaf state node is _closed_ if it has no successor and is not a goal
- An action node is solved if all its children are solved
- An action node is closed if at least one of its children is closed
- Keep labeling nodes upward until the root
- The problem is solved when the root node is solved
- The problem is impossible if the root node is closed
- The _solution_ is the subtree that establishes that the root is solved
- It defines a conditional plan that includes tests on sensory data to pick the next action

## When a node is the same as an ancestor node
- Create a loop
- Label:
    - Solved.
        Meaning: The solution is a conditional plan that includes loop
        While (Robot in R1) do Right
        Depends on the cause of non deterministic action, may / may not work
    - Closed
        Meaning: No solution through the node

## Search a AND-OR Tree
- Start from a state node (OR level)
    - Fringe nodes are state nodes
- Use any search algorithms we have studied
    - Select a fring node to expand
    - Select an action to use
    - Insert the corresponding action node
    - Insert all possible outcome of the action, as the child of the action node
    - Backup to (re-)label the ancestor nodes
- Cost calculation at AND level
    - Weighted sum (when uncertainty is quantified using probability, expectation)
    - Take the minimum

## Basic idea of a Minmax algorithm
- Using the current state as the initial state, build the game tree to the maximal depth h (called horizon) feasible within the time limit
- Evaluate the states of the leaf nodes
    - Use heuristic as an evaluation function to estimate how favorable a state is
- Backup the results from the leaves to the root and pick the best action assuming the worst from MIN
    - At each non-leaf node N, the backed up value is the value of the best state that MAX can reach at depth h if MIN plays well (by the same criteria as MAX applies to itself)
    - Same criterion: same evaluation function

## Evaluation function
- Need a heuristic to estimate how favorable is a game state for the agent (MAX)
    - Usually called evaluation function `e: S -> R`
    - `e(s) > 0: s` is favorable to MAX (the larger the better)
    - `e(s) < 0: s` is favorable to MIN
    - `e(s) = 0: s` is neutral

## Construction of an Evaluation Function
- Usually a weighted sum of "features"
