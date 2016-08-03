# Week 2 Tute

## What makes an AI?
- Act like a human
- Think like a human
- Act rationally

## Agents
- State Space (S)
Configuration of environment

- Percept Space (Z:S -> P)
What the agent sees

- Action Space (A)
Things the agent can do

- World Dynamics (T: S x A -> S`)

- Utility Function

## Tutorial 1

### Q1: Design an agent that plays and wins Tic Tac Toe

Action Space (A): {0, 1, 2, 3, 4, 5, 6, 7, 8} (Position in Tic Tac Toe board)
Percept Space: The current layout of the board at a given 'move'
State Space (S): All possible Tic Tac Toe games
World Dynamics (SXA -> S)
Utility: +1 for goal state, 0 for all other states. Where a goal state is any possible move that could lead to success
Initial state: Empty board
Goal state: Any state where the agent wins
