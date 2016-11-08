# Psuedocode Examples

## Value Iteration

```
    Initialize V[0](s) = R(s) for all s

    Loop:
        For all s in S {
            V[t+1](s) = max(R(s) + d * sum(T(s, a, s') * V[t](s'))
        }

        t += 1
    Until V[t+1](s) = V[t](s) for all s
```

## Policy Iteration

```
    Initialize V[0](s) = R(s)
    Loop:
        pi'(s) = argmax(R(s) + d * sum(T(s, a, s') * V[t](s'))
        t += 1
```

## Q-Learning

```
Initialise Q(s, a) arbitarily
Repeat for each episode:
    Initialise s
    Repeat for each episode:
        Choose a from s using policy derived from Q (e.g. e-greedy)
        Take action a, observe r, s'
        Q(s, a) = Q(s, a) + e[r + d * max(Q(s', a') - Q(s, a)]
        s = s'
    until s is terminal
```

## SARSA

```
Initialise Q(s, a) arbitarily
Repeat for each episode:
    Initialise s
    Repeat for each episode:
        Choose a from s using policy derived from Q (e.g. e-greedy)
        Take action a, observe r, s'
        Choose a' from s' using policy derived from Q (e.g. e-greedy)
        Q(s, a) = Q(s, a) + e[r + d * max(Q(s', a') - Q(s, a)]
        s = s'
        a = a'
    until s is terminal

```
