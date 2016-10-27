#!/usr/bin/env bash

ant
java -jar ./dist/lib/a3-comp3702-43560846.jar tests/medium-4.txt medium.out solver.OfflineSolver
