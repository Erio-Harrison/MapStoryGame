#!/bin/bash

# uncomment to download gson
#curl -OJ https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.2/gson-2.8.2.jar
# mkdir -p lib
#mv gson-2.8.2.jar lib/

mkdir -p doc
gson_jar="lib/gson-2.8.2.jar"
javac -cp "$gson_jar" -sourcepath . src/GameEngine/*.java
javadoc -cp "$gson_jar" -d doc/ src/GameEngine/*.java
java -cp "src:$gson_jar" GameEngine.GameLoader config.json
