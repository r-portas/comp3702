# Week 11: Introduction to Machine Learning

## What is Machine Learning
- Algorithms to enable agents to __improve__ their behaviour with __experience__
    - Improve: There's a performance measure
    - Experience: Feedback / Observations the agents perceived
- Components to consider
    - Performance Measure
    - Representation: Bayes Network, real functions
    - Types of feedback: Win/lose at the end of a game, immediate outcome, etc.

## Types of Machine Learning
- Supervised Learning
    - Learn from examples
    - Sometimes we are not sure if the examples are correct or not
        - Semi supervised learning
        - Becoming more popular with crowd sourcing
- Unsupervised Learning
    - Learn by finding a structure in the data
- Reinforcement Learning
    - Learn by doing

## Supervised Learning
- Given a set of data points, find a function such that the x values accurately predict the v values
- Many possible hypothesis that fits the training data: Linear, polynomial, etc.
- Ockham's Razor Principle: Choose the simplest hypothesis that fits the data

## Unsupervised Learning
- The training data does not need the results
    - No need to annotate the data
- Look for patterns in the data
    - A lot of techniques rely on some notion of distance
    - Nearest neighbor clustering

## Decision Tree: Simplest Supervised Learning Method
- Decision tree is a tree where:
    - Non-leaf Nodes: Attributes (the x values)
    - Leaf Nodes: Results (the y values)
    - Edges: The values of the attributes

### Building a Decision Tree
- Given: A set of training data
- Goal: Build the smallest possible decision tree consistent with the training data
- A simple method: Greedy
- Assuming that we will only include one attribute predicate in the decision tree, which attribute should we test to minimize the probability of error
    - Greedy with respect to the probability of misclassification
    - Greedy with respect to the information gain

### Probability of Misclassification
- Compute probability by counting
- Choose the attribute that minimizes the number of false positives and false negatives

### Information Gain
- Entropy: A measure of uncertainty
- Information gain for testing an attribute that maximizes Gain

### Decision Tree Learning - Greedy
```
DTL (Training data)
1. If all Ys in training data are positive then return True
2. If all Ys in training data are negative then return False
3. If attribute is empty then return failure
4. A <- Error minimizing attribute
5. Return the tree whose:
    - root is A
    - left branch is DTL(D+A)
    - right branch is DTL(D-A)
```

Too much training data could lead to overfitting.

## Neural Network

- A network of (artificial) neurons
- A neuron: a function applied to a linear combination of input attributes
    - The function is often called the activation function
    - Some commonly used: simple thresholding, logistics

### Types of Neural Networks
- Feed Forward: acyclic graph
    - Single layer (simplest)
    - Multi Layer
- Recurrent
    - At least one cycle
    - Cycle represents feedback connection

## Bayesian (Belief) networks
- A compact representation of conditional dependency relation between random variables
- Consists of a graph and conditional probability tables
- The graph:
    - Set of nodes that represents a set of random variables. Each varaible has a finite set of values
    - Set of directed edges that represent dependency relationship between the corresponding random variables
- A conditional probability table (CPT) represents conditional probability of a node given its parents

### Markov Blanket
- Simplify probability computation
    - Easy to spot conditional independence relationship

- A variable in a Bayes network is conditionally independent from all others, given its Markov blanket.
- Markov blanket of a node X consists of:
    - Parents of X
    - Children of X
    - Parents of children of X

### Inference
- Compute: Probability of an event knowing one or more other events are happening
- To answer any query involving a conjunction of variables, sum over the variables not involved in the query

## Reinforcement Learning
- Reinforcement learning is MDP where the transistion and/or reward functions are not known
- Need to try and explore the environment

### Solving the Reinforcment Learning MDP
- Data from interacting with the world: `<s, a, r, s'>`
- Model based vs model free: Whats being learned
- Passive vs Active: How the data is being generated

Model Based:
- Use data to learn the missing components of the MDP problem, i.e. T & R
- Once we know T & R, solve the MDP problem
- Indirect learning, but most efficient use of data

Model Free:
- Use data to learn the value function and policy directly
- Direct learning, not the most efficient use of data, but sometimes faster

Passive:
- The agent is given the set of data
- The agent does not need to decide what action to perform
- Recently introduced

Active:
- The full reinforcement learning problem
- The agent selects what action to perform and the action performed determine the data it receives, which then determines how fast the agent converges to the correct MDP model
- Exploration vs combination

