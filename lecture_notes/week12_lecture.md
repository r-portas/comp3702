# Week 12: Machine Learning

## Inference
- Compute: Probability of an event knowing one or more other events have happened
- To answer any query involving a conjuction of variables, sum over the variables not involved in the query

## Variable Elimation Algorithm
- Idea: Use conditional independence to reduce the number of combinations we need to calculate

## Ways to generate a Bayesian Network
- Human expects (usually ok only for structure)
- Learn from data

## Exploration vs Exploitation
- Methods:
    - Epsilon-greedy
    - EXP3
    - UCB

## Epsilon-greedy
- Assign a weight to each sampling strategy
- Start with equal weight for all strategies
- Strategy with the highest weight is selected with probability `(1 - e)`. The rest are selected with probability `e/N`, where `N` is the number of strategies available
- Suppose strategy `s1` is selected, we'll use `s1` to sample and add a vertex edges to the roadmap

## EXP3
- At least competitive to the best strategy

## Upper Confidence Bound (UCB)
- Its a thing?

## Approaches for Solving
- Data for interacting with the world: `<s, a, r, s'>`

## Model based vs Model free
- Model based
    - Use data to learn the missing components of the MDP problem
    - Once we know T & R, solve the MDP problem
    - Indirect learning, but most efficient use of data
- Model free
    - Use data to learn the value function and policy directly
    - Direct learning, not the most efficient use of data, but sometimes can be fast

## Passive vs Active
- Passive
    - The agent is given the set of data (e.g. from video)
    - The agent does not need to decide what action to perform
    - Recently introduced
    - Similar to supervised learning, but the annotation is implicit
- Active
    - The classical reinforcement learning problem
    - The agent selects what action to perform, and the action performed determine the data it receives, which then determines how fast the agent converges to the correct MDP
    - Exploration vs Exploitation
- Combination of the two

## Model Based Approach
- Two steps, ran iteratively
- Loop over:
    - Learning step: Use data to estimate T & R
    - Solve the MDP as if the learned model is correct
- Learning the T & R steps:
    - Use counting to estimate T & R

## Monte Carlo
- Goal: Given a policy, learn the value of the policy when T & R are unknown
- Assumption: Episodic MDP
    - Each episode (i.e. each run) is guarenteed to terminate in a finite amount of time
- Loop Over:
    - Generate an episode
    - Compute the total discounted reward for the episode
    - Update the value

- First visit:
    - Only update the value of a state if it is visited the first time in the sampled episode
- Every visit:
    - Update the value of a state whenever it is visited (regardless at which time step)
- Conver to the true value by law of large numbers

## Temporal Difference (TD) Learning
- One of the most famous Reinforcement Learning approach
- Idea: Iteratively reduce the difference between the value or Q-value estimates
- Given a policy, suppose the reinforcement learning agent traverse the following episode

## Monte Carlo vs Temporal Difference Learning
- Monte Carlo approach updates value after the episode is finished
- Temporal difference updates value after each step

## TD Variants:
    - Q-learning
    - SARSA (State Action Reward State Action)
    - General TD

## Q-learning
- Off-policy: Update the Q-value based on the (estimated) best next actions, even though its not the action performed
    - The policy being followed is not the same as the policy being evaluatedQ

## SARSA
- On-policy: Consider the actual action that the agent will take at the next state
- Data is `(s, a, r, s', a')`


