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

## Configuration Space
- A configuration is the parameters that uniquely defined the position of each point on the robot
- Forbidden region: The set of configurations that will cause the robot to collide with the obstacles in the environment
- Free space: C-space \ forbidden region


