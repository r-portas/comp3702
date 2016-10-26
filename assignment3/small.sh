#!/usr/bin/env bash

ant
java -jar ./dist/lib/a3-comp3702-43560846.jar tests/small-v1.txt small.out solver.OnlineSolver
