#!/usr/bin/env bash

ant
java -jar ./dist/lib/a3-comp3702-43560846.jar tests/large-4.txt large-4.out solver.OnlineSolver
