# Week 5 Tutorial

## Q1

```
A: Trunk A has a treasure
B: Trunk B has a treasure

A v B
~A

If both true: 
(A v B) <-> ~A
(A v B) -> ~A ^ ~A -> (A v B)

Only works if A is False and B is True
```

## Q2

```
G: Gold road
M: Marble road
S: Stone road

~(G ^ (S -> M))
~G v ~(S -> M)
~G v ~(~S v M)
~G v (S ^ ~M)

~(~G ^ ~S)
G v S

~(G ^ ~M)
~G v M

(~G v (S ^ ~M)) ^ (G v S) ^ (~G v M)

(G, S, M)
(0, 1, 0): True

Thus take the stone road
```

## Q3

```
B: Bumper car
C: Carousel
R: Roller Coaster
H: Haunted Class
F: Ferris Wheel

B v C

~B -> R
B v R

C -> (B v H)
~C v B v H

H -> F
~H v F

~(B ^ F)
~B v ~F

R -> F
~R v F

~R -> (H v F)
R v H v F

Combine together
(B v C) ^ (B v R) ^ (~C v B v H) ^ (~H v F) ^ (~B v ~F) ^ (~R v F) ^ (R v H v F)

Trial and Error:
B = 1, F = 0, H = 0, R = 0...

There is no combination of rides that satisfy the constraints
```
