# Week 3 - Informed Search and Search in Continuous Space

## Completeness Definition

Each and every ancestor of the goal node must be expanded after a finite number of steps.

## Optimality Definition

Show that the first time a goal node is expanded, the path to this goal node is optimal.

## Revisiting a Node

Revisiting nodes can increase the time complexity of the algorithm. However if they are ignored, 
the most optimal solution might not be found.

## Consistent Heuristics

A consistent heuristic must be always at most equal to the estimated distance from any neighboring vertex to the goal plus the step cost
vertex to the goal plus the step cost of reaching the neighbor.

A consistent heuristic must also be admissable.

## Generate Heuristics

- Information about the problem
- Knowledge about the sub problems
- Learn from prior results of solving the same or similar problems

## Motion Planning
- Motion planning is the study of computational methods to enable an agent to compute its own motions

## Simple Motion Planning Problem
- A Point robot operating in a 2d space with obstacles of known shape and position
- A robot is an agent that operates in the real world or within a simulation

## Visibility Graph
- Obstacles can be represented as polygons
- Can be modelled as a undirected graph where:
    - Nodes are vertices of the obstacles
    - An edge between two vertices represents an edge of a polygon
- Given the initial and goal states, find a vertex nearest to the initial state where the path to that vertex is collision free
- If each edge are labels with the length of the path the edge represents, the shortest path can be found by finding the shortest path in the graph
- Space complexity is O(n^2)

## Uniform Grid Discretization
- Obstacles do not have to be represented as polygons
- Each grid cell that does not intersect with an obstacle becomes vertex in the state graph
- Use a search on state graph as usual
- Problems:
    - As the number of joints increases, dimensionality of state space increases
    - As the number of vertices in state space grows exponentially when the dimension of the state space is increased
    - The number of out edges grows exponentially with the dimension of the state space
- A better alternative is to build a small state graph that captures only the 'important features' of the state space
- Effectively using sampling to build the graph

## Configuration Space
- A configuration is the parameters that uniquely defined the position of each point on the robot
- Forbidden region: The set of configurations that will cause the robot to collide with the obstacles in the environment
- Free space: C-space \ forbidden region

## Probabilistic Roadmap (PRM)
- Sample a set of states uniformly at random
- Loop and sample a configuration, check if it is not a collision then add it as a vertex to the state graph
- PRM summary: State space -> State graph -> Search in a graph
- Use sampling to construct a state graph
- Key components:
    - Sampling strategy (adding vertices)
    - Connection strategy (adding edges)
    - Check if a configuration is valid or not
    - Check if a line segment in C-space is valid or not

## Interleave
- Interleave state graph constructing and graph search
- Be careful of time complexity


## Sampling Strategies
- Sample near obstacles

Sample a configuration (q1) uniformly at random

Sample another configuration (q2) from the set of all configurations within D distance from the first (q1), uniformly at random

If q1 is in-collision and q2 is collision free: Add q2 as a vertex in the state graph

If q2 is in-collision and q1 is collision free: Add q1 as a vertex in the state graph

If q1 and q2 are in-collision, check the middle configuration between the two. If it is collision free, add it to the state graph

- Sample inside a passage
- Using workspace information
- Combining sampling statergies
