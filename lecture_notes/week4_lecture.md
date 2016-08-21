# Week 4: Search in Continuous Space

## Probabilistic Roadmap

State Space -> State Graph - Search in Graph

- Use sampling to construct the state graph
- Components:
    - Sampling strategy (adding vertices)
    - Connection strategy (adding edges)
    - Check if a configuration is valid or not
    - Check if a line segment in C-space is valid or not

## State Graph (Roadmap) construction
```
loop:
    Sample a configuration q with a suitable sampling strategy
    if q is collision free then:
        Add q to the graph G
        Connect q to existing vertices in G using valid edges
```

## Sampling Strategies

### Sample near obstacles

- Sample a configuration (q1) uniformly at random
- Sample a configuration (q2) from the set of all configurations within D distance from q1, uniformly at random
- If q1 is in-collision and q2 is collision free:
    - Add q2 as a vertex in the state graph
- Else if q1 is collision free and q2 is in collision:
    - Add q1 as a vertex in the state graph

### Sample inside a passage

- Sample a configuration (q1) uniformly at random
- Sample a configuration (q2) from the set of all configurations within D distance from q1, uniformly at random
- If q1 and q2 are in-collision:
    - Check if the middle configuration `qm = 0.5(q1 + q2)` is collision free
    - If qm is collision free, add qm as a vertex in the state graph

### Using workspace information

- Narrow passages in C-space are often caused by narrow passes in the workspace
- Relax problem into planning for a point robot
    - Discretize the workspace into uniform grid
    - Choose a point r on the robot
    - Find a path t assuming the robot is at point r
    - t: sequence of grid cells
- To sample a configuration
    - Sample a cell c in t with equal probability
    - Sample a point p uniformly at random from c
    - Sample a configuration uniformly at random from the set of all configurations that place point r of the robot at p

### Combining sampling strategies

- Analogous to combining heuristics
- See multi arm bandit problem

## Why sampling strategies work

- There are abundant solutions, we only need one

