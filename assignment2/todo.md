# TODO
- Implement more sampling methods
    - Implement sampling method for generic joint angles
- Find a midpoint between two configurations
- Modify ArmConfig to allow sampled configs to have a _quantum_ joint state
    - Once sampling is complete, sample different joint configurations on top of a single chair base
    - If the current node is not the destination, ignore the final joint check

## TODO: Only generate primitive steps AFTER a solution is found

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
