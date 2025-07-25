#!/bin/bash

# Manual compilation script for environments without Maven
# This script compiles the Java package manually using javac and jar

set -e

echo "Manual compilation of SwissEph Java..."

# Define directories
SRC_DIR="src/main/java"
TEST_DIR="src/test/java"
BUILD_DIR="target/classes"
TEST_BUILD_DIR="target/test-classes"
NATIVE_DIR="src/main/native"

# Create build directories
mkdir -p $BUILD_DIR $TEST_BUILD_DIR

echo "Step 1: Building native library..."
chmod +x scripts/build-native.sh
./scripts/build-native.sh

echo "Step 2: Compiling Java sources..."
javac -d $BUILD_DIR $SRC_DIR/io/github/prolaxu/swisseph/*.java

echo "Step 3: Compiling test sources..."
javac -cp $BUILD_DIR -d $TEST_BUILD_DIR $TEST_DIR/io/github/prolaxu/swisseph/*.java

echo "Step 4: Creating JAR file..."
jar -cvf target/swisseph-java-2.10.03.jar -C $BUILD_DIR .

echo "Step 5: Creating JAR with native library..."
mkdir -p $BUILD_DIR/native
cp $NATIVE_DIR/*.so $BUILD_DIR/native/ 2>/dev/null || true
cp $NATIVE_DIR/*.dll $BUILD_DIR/native/ 2>/dev/null || true
cp $NATIVE_DIR/*.dylib $BUILD_DIR/native/ 2>/dev/null || true
jar -cvf target/swisseph-java-2.10.03-with-natives.jar -C $BUILD_DIR .

echo ""
echo "✅ Manual compilation completed successfully!"
echo ""
echo "Generated artifacts:"
echo "  - target/swisseph-java-2.10.03.jar (main JAR)"
echo "  - target/swisseph-java-2.10.03-with-natives.jar (JAR with native libs)"
echo "  - $NATIVE_DIR/libswisseph.so (native library)"
echo ""
echo "To run tests:"
echo "  java -Djava.library.path=$NATIVE_DIR -cp target/swisseph-java-2.10.03.jar:$TEST_BUILD_DIR io.github.prolaxu.swisseph.AllFunctionsTest"
