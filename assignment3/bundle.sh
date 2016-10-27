#!/usr/bin/env bash

echo "Bundling assignment"

# Create the destination directory
rm -rvf a3-comp3702-43560846
mkdir a3-comp3702-43560846

# Copy files into a new folder
cp -v report/a3-comp3702-43560846.pdf a3-comp3702-43560846/a3-comp3702-43560846.pdf
cp -v build.xml a3-comp3702-43560846/build.xml

mkdir a3-comp3702-43560846/src
cp -rv src/* a3-comp3702-43560846/src
cp -v dist/lib/a3-comp3702-43560846.jar a3-comp3702-43560846/a3-comp3702-43560846.jar

# Zip the folder
#zip -r a3-comp3702-43560846.zip a3-comp3702-43560846
