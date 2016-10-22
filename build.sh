#!bin/bash
./gradlew bootRepackage
docker build -t b4456609/easylearn-pack:latest -t b4456609/easylearn-pack:${1} .
