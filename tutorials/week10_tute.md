# Week 10 Tutorial

## Q1

### a: Define the POMDP framing of this problem

Reward function, possible choices:
- R(s)
- R(s, a)
- R(s, a, s')

```
s -> {(T, L), (L, T)}
A -> {Open L, Open R, Listen}
R -> R(s, a)
T -> T(s, a, s')
O -> {Tiger left, Tiger right}
Z -> P(o | s, a)    -> P(Tiger L | (T, L), LISTEN} = 0.85
                    -> P(Tiger R | (T, L), LISTEN} = 0.15
```

Notes:
- O, Observation space

### b: 

See belief update function in PODMP lecture, the belief update function
uses the obsevation function.

Belief update function generates a belief vector, the sum of the belief vector does not sum to 1.

We use the transition function to sample where we can get to.

b(s):

| (T, L) | (L, T) |
| ------ | ------ |
| 0.5    | 0.5    |

After step (Intuition):

| (T, L) | (L, T) |
| ------ | ------ |
| 0.85   | 0.15   |
