# Week 2 Lecture

## Intro to Search
- Finding the solution by exploring possiblities
- Find a sequence of actions to move the agent from the initial state to the desired state

## General Structure of Search Algorithms
- Put initial vertext in a 'container' of states to be expanded
- Loop:
  - Select a vertex v from the 'container'
  - If its the vertex goal, return
  - Expand v (put the results of successor(v) into the 'container')
- successor(v), a function that:
  - <todo>
- Algorithms differ on what node to expand next
  
## Search Tree
- An abstract representation of visited nodes (expanded + container)
- If the states can be revisited, the search tree might be infinite
- Fringe Nodes: Nodes that have not been expanded yet

## Performance Measure of Search Algorithms
- Completeness - The algorithm will find a solution if one exists
- Optimality - Return the minimum path cost if one exists
- Complexity - Time and Space complexity

## Uninformed Search (Blind Search)
- Does not estimate the cost from the current node to the goal
- Methods:
  - Breadth First Search (BFS)
  - Depth First Search (DFS)
  - Interative deepening DFS
  - Uniform cost search

### Breadth First Search
- Cost: Number of steps (ignores cost on the edges)
- Select a fringe node in the same level of the search tree, before selecting fringe nodes at the next level
- Use queue (FIFO) to store fringe nodes
- BFS is complete, since it will find every solution
- BFS is optimal for the number of steps, but not cost of each path
- The time and space complexity of BFS is O(b^d)

#### Bidirectional Strategy for BFS
- Use 2 fringe queues, one at the starting point and one at the desired point

### Depth First Search
- Cost: Number of steps (Ignore cost on the edges)
- Expand a fringe node most recently inserted into the tree
- Use a stack to store fringe nodes
- DFS is complete, since it will find every solution
- DFS is not optimal, because it takes the first option every time
- The time and space complexity of DFS is O(b^d)

### Interative deepening DFS
