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
- **Finds minimum step path, but requires exponential space**

#### Bidirectional Strategy for BFS
- Use 2 fringe queues, one at the starting point and one at the desired point

### Depth First Search
- Cost: Number of steps (Ignore cost on the edges)
- Expand a fringe node most recently inserted into the tree
- Use a stack to store fringe nodes
- DFS is complete, since it will find every solution
- DFS is not optimal, because it takes the first option every time
- The time complexity of DFS is O(b^m)
- The space complexity is O(bm) or O(m) using backtracking DFS
- **Efficient is use of space**

#### Bidirectional Strategy for DFS
- Is not suitable for DFS

### Interative deepening DFS
- Multiple DFS with increasing depth cutoff until the goal is found
- It is complete if the branching factor is finite
- It will generate the optimal solution (only in terms of steps)
- The time complexity is O(b^d)
- The space complexity is O(bd)

### Uniform Cost Search
- Expand fringe node with lowest cost from root
- Use a Priority Queue to store fringe nodes
- It is complete as long as the the branching factor is finite and all edges have a cost
- It is optimal in terms of cost if all edges have a positive cost
- The time and space complexity is O(b^(1 + floor(c\*/e))) where c* is the cost of the optimal solution and e is the minimum cost of the step

## Informed Search
Informed Search: Select which node to expand based on a function of the estimated cost from the current node to the goal state

Cost: f(n) = g(n) + h(n), where g(n) is the cost from root to node n and h(n) is the estimated cost from n to goal (usually based on heuristics)

The node is selected based on f(n) and f(n) must contain h(n)

### Blind vs Informed Search
- Bias the search towards the goal
- In general, informed searches are faster but highly depends on the heuristics used

### Greedy Best First Search
- Expand fringe node with lowest estimated cost from the current node to the goal
- f(n) = h(n), g(n) is ignored
- Expand node with the lowest f(n)
- Almost the same as Uniform Cost Search
- Use priority queue (PQ) to keep fringe nodes
  - The highest priority in PQ is the node with the smallest estimated cost (using the heuristic function)
- Is is not complete (based on heuristic)
- Is will not generate the optimal solution (based on heuristic)
- The Time and Space complexity depends on the heuristic, worst case is O(b^m)

### A* Search
- Expand fringe node with lowest estimated cost from root to goal via the node
- g(n): Cost from root to node n
- h(n): Estimated cost from node n to goal
- f(n) = g(n) + h(n)
- Expand fringe node with lowest f(n)
- Use priority queue to store fringe nodes
  - Highest priority is the node with the lowest f value, where f = h(x) + g(x)
- It is complete, as long as every edge has a minimum cost
- It is optimal as long as every edge has a minimum cost and the heuristic is admissible
- Complexity is heavily influenced by the heuristic
- Is similar to Breadth First Search but with weighted costs

### Admissible Heurisitics
- Should never overestimate the cost
- The heuristic needs to be less than or equal to the true cost to reach the goal
  
