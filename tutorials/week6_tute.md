# Week 6 Tutorial

## Q1

From last week, we have the CNF form as:
`(B v C) ^ (B v R) ^ (~C v B v H) ^ (~H v F) ^ (~B v ~F) ^ (~R v F) ^ (R v H v F)`

1. Create a tree where the above CNF form is the root
2. The tree edges are possible values, for example B = True, B = False
3. The child nodes are the parent node's CNF equation with the value subsituted in
4. Keep on making child nodes until you reach a single result, either True or False

## Q2

The 8 puzzle problem is single player, which means there is not another player to change the state of the problem. Thus all the states are known and there is no uncertainty to the game.

## Q3

__State Space__: Discretize the court into a set of tiles

__Action Space__: Every possible action to hit the ball (forward, left, right, stay)

__World Dynamic__: Agent will move every second time increment

__Utility__: The probability of winning the point

