# TODO
- Implement more sampling methods
    - Implement sampling method for generic joint angles
- Find a midpoint between two configurations


## Psuedo Algorithm
```
sampleList = []

for i in range(0, 100):
    x = random sampled valid configuration
    sampleList.append(x)

for i in range(0, 200):
    x = valid configuration between any two points
    sampleList.append(x)
```

The aim of the above algorithm is to add more valid configurations between points.
