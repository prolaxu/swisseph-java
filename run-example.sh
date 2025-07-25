#!/bin/bash
# Run the SimpleExample with the native library path and classpath set
java -Djava.library.path=src/main/native -cp "target/classes:target/dependency/*" io.github.prolaxu.swisseph.SimpleExample
