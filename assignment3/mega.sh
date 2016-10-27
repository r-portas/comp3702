#!/usr/bin/env bash

ant
java -jar ./dist/lib/a3-comp3702-43560846.jar tests/mega-4.txt mega-4.out solver.OnlineSolver
