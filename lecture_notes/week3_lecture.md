# Week 3 - Informed Search

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

