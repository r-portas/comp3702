# Revision Lecture

## Agents
- Make good decisions when information about the problem is accurate and abundant
- Make good decisions when information about the problem is inaccurate and limited
- Learn and improve their decision making capability over time

## What is AI
- An attempt to build "intelligent" computers
- What is "inteligent"
    - Act like humans
    - Think like humans
    - Act rationally

## What is an Agent
- A computer that:
    - Gathers information about an environment
    - Takes actions autonomously based on that information
- Examples:
    - A robot
    - A web crawler
    - A spam filter

## Building an Agent
- We need to solve 2 problems:
    1. Modelling (i.e. defining the agent by way of defining components of the agent)
    2. Solving the problem that the agent should solve

## Components of an Agent
- Action Space (A)
- Percept Space (O)
- State space (S)
- World dynamics (T: SXA -> S)
- Percept function (Z: S -> O)
- Utility function (U: S -> real number)

## Problems the Agent Should Solve
- Find a mapping from sequences of percepts to action P* -> A that maximizes the utility function
- Given the sequences of percepts it has seen so far, what should the agent do next, so that utility function can be maximized

## Classes of Environemnt
- Properties about the environment itself or the agent's knowledge about the environment:
    - Discrete vs Continuous
    - Deterministic vs Stochastic
    - Fully observable vs Partially observable
    - Static vs Dynamic

## Formulating a Problem as a Search Problem
- Similar to formulation of a rational agent:
    - Action space
    - Percept space
    - State space
    - World dynamics
    - Percept function
    - Utility: Cost function
    - Initial and Goal state
- Find a sequence of actions to move the agent from being in the initial state to being in the goal state, such that the cost of moving is minimized

## General Structure of Search Algorithm
```
Put initial vertex in a "container" of states to be expanded
Loop:
    Select a vertex v from the "container"
        If v is the goal vertex, then return
        Expand v (i.e. put the results of successor(v) to the "container")
successor(v), a function that:
    Takes a vertex v as input
    Output the set of immediate next vertices that can be visited v (the end point of out edges from v)
```

## Performance Measure of Search Algorithms
1. Completeness
    - Complete: The algorithm will find the solution whenever one exists
    - What happened when no solution exists?
2. Optimality
    - Optimal: Return a minimum cost path whenever one exists
3. Complexity
    - Amount of time and memory needed to solve the problem
    - Use big-O notation

## Types of Deterministic Search

- Blind Search
    - Do not use any additional information to "guess" cost of moving from current node to goal
    - Example: DFS, BFS, Iterative Deepening DFS, Uniform Cost Search
- Informed Search
    - Use additional information to "guess" cost of moving from current node to goal and decide where to explore next using this information
    - Example: Best First Search, A* Search

## Search in Continuous Space
- Construct discrete state graph
    - Uniform Grid Discretization
    - Visibility Graph
    - Random Sampling, PRM, RRT, RST
- Use the search algorithms we've discussed so far to find a path in the state graph

## An Application in Motion Planning
- Motion Planning: The study of computational methods to enable an agent to compute its own motions to moving from a given inital to a goal state
- Represent the problem: C-Space, Forbidden Region
- Solve using continuous search method
