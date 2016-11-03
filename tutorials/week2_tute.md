# Week 2 Tutorial

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


## Q1: Design an agent that plays and wins Tic Tac Toe

Action Space (A): {0, 1, 2, 3, 4, 5, 6, 7, 8} (Position in Tic Tac Toe board)

Percept Space: The current layout of the board at a given 'move'. 
- Transition state: xexoe... -> xoxoe

State Space (S): All possible Tic Tac Toe games

World Dynamics (SXA -> S) The change of the board from the other player

Utility: +1 when the agent makes a straight line, -1 when the opponent makes a straight line. 0 for any other state.

Initial state: Empty board

Goal state: Any state where the agent wins

## Q2: Navigation App

### A) How will you design it?

There will be two points the agent will use, the current location and the desired location. The agent can use a map of roads to find the shortest path.

Action Space: An array of routes the agent can follow to reach the destination

Percept Space: All possible routes between the two points

State Space: All possible routes an agent can take

Initial State: The starting point

Goal State: The destination

World Dynamics: Traffic

Utility: -x, where x is the time taken to go a selected route, agent chooses the highest value

### B) What is the type of environment the agent will act in

- Discrete
- Fully observable
- Deterministic
- Static

### C) Define the search problem and its corresponding state graph representation

This will be an informed search problem which factors in the time taken to complete a route to calculate the total cost of a route.
