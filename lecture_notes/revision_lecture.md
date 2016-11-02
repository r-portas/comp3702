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

## Logic as a way to represent abstraction of states
- A formal language to represent sets of states
- A convenient abstraction for dealing with many states
- Recall in PRM: We can view a vertex in a roadmap to represent a set of "nearby" states
- Regardless of whether there's a natural notion of "near" or not, we can use logic to group different states together

## Many Types of Logic
- Propositional Logic
- Predicate / First Order Logic
- High Order Logic

## Two Types of Problems
1. Validity
    - The sentence is true under all interpretations
    - Methods:
        - Simple Model Checking
        - Theorem Proving
            - Natural Deduction
            - Resolution Refutation
2. Satisfiability
    - There is at least one interpretation that makes the sentence to be true
    - Methods: Model Checking (DPLL & GSAT)

## Causes of Uncertainty/Non Determinism
- System noise and errors, e.g. due to external disturbances, measurement noises and errors, actuators noise
- Modelling error, e.g. due to abstraction, simplification, accidental
- Lack of information

## Making Decisions in a Non Deterministic World
- We want to find a plan that works regardless of what outcomes actually occur
    - Can no longer rely on a sequence of actions
    - Need a conditional plan. The action to perform depends on the outcome of previous action
- Representation: Some form of AND-OR Tree

## Non Stochastic
- Hedge over all possible scenario
    - Planning with AND-OR tree
- Game Scenario (Zero Sum)
    - Assume the opponent is rational: minimax algorithm, min-max tree
    - More efficient: alpha-beta pruning

## Stochastic Uncertainty and Decision Theory
- Quantify uncertainty using probability
- Simplest form: Maximum Expected Utility (MEU)
- Markov Decision Processes (MDPs)
- Partially Observable Markov Decision Processes (POMDPs)

## Decision Theory
- A framework for an agent to choose the "best" decision in non-deterministic environment
- Using this framework, a problem is represented as:
    - A set of states (primitive outcomes)
    - Preference: Which outcome is prefered
        - `A > B`: Agent prefers A over B
        - `A = B`: Agent is indifferent between `A` and `B`
    - Lotteries: A set of possible outcomes with their probability of occuring, e.g. `L = [Pa, a; Pb, B]`
    - Represents probability of non determinism of an action

## Maximum Expected Utility (MEU)
- Main problem: What does "best" mean?
- Utility: A number that assigns the desirability of a state
- MEU is the commonly used definition of "best" action
- Idea:
    - Assigns utility function to each outcome (state) to represent the agent's preferences
    - "Best" decision maximizes the expected utility of the outcomes

## Defining an MDP Problem
- Formally defined as 4-tuples (SATR)
    - S: State space
    - A: Action space
    - T: Transition function, `T(s, a, s') = P(S(t+1) = s' | St = s, At = a)` 
    - R: Reward function, `R(s) or R(s, a) or R(s, a, s')`

## Using a Policy
1. Starts from the initial state
2. Move according to the policy
3. The agent moves to a new state
    - The new state the agent ends up may be different in different runs
4. Repeat to 2 until stopping criteria is satisfied (e.g. goal is reached)

## Solving an MDP Problem
- Meaning: Finding an (close to) optimal policy
- Methods:
    - Offline: Value iteration and Policy iteration
    - Online: RTDP and MCTS

## Partially Observable Markov Decision Processes (POMDPs)
- Main Components:
    - State space (S) - not known
    - Action space (A)
    - Observation space (O)
    - Transition function (T)
    - Observation function (Z)
    - Reward function (R)

- Model
    - Belief: Distrubution over the state space
    - Strategy/Policy: Mapping from beliefs to actions

## Types of Machine Learning
- Supervised Learning
    - Learning from examples
    - Sometimes, we're not sure if the examples are correct or not
        - Often called semi-supervised learning
        - Becoming more popular with crowd sourcing
- Unsupervised Learning:
    - Learning by finding structure in data
- Reinforcement Learning:
    - Learn by doing

## Representations of Supervised and Unsupervised Learning
- Decision Tree
- Neural Network
- Bayesian Network

## Reinforcement Learning - Defined
- Reinforcement learning is MDP where the transition and/or reward functions are not known
    - S: State space
    - A: Action space
    - T: Transition function
    - R: Reward Function
- Need to try and explore the environment

## Solving
Solving a reinforcement learning problem means computing the best action to preform (recall the MDP definition of best action), even thought the transition and reward function are not known prior.

- Model Based
    - Passive: Some supervised learning can be used
    - Active: POMDP for Bayes RL
- Model Free
    - Monte Carlo
    - Temporal Difference (TD)
    - Q-Learning, SARVA
    - TD
