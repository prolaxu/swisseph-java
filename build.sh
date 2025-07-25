#!/bin/bash

# SwissEph Java Build Script
# This script builds the complete Maven project including native libraries

set -e

echo "Building SwissEph Java..."

# Clean and compile
echo "Step 1: Cleaning previous builds..."
mvn clean

echo "Step 2: Compiling native libraries..."
chmod +x scripts/build-native.sh
./scripts/build-native.sh

echo "Step 3: Compiling Java sources..."
mvn compile

echo "Step 4: Running tests..."
mvn test

echo "Step 5: Creating JAR packages..."
mvn package

echo ""
echo "✅ Build completed successfully!"
echo ""
echo "Generated artifacts:"
echo "  - target/swisseph-java-2.10.03.jar (main JAR)"
echo "  - target/swisseph-java-2.10.03-sources.jar (sources JAR)"
echo "  - target/swisseph-java-2.10.03-javadoc.jar (javadoc JAR)"
echo "  - src/main/native/libswisseph.so (native library)"
echo ""
echo "To run tests manually:"
echo "  mvn test"
echo ""
echo "To install to local Maven repository:"
echo "  mvn install"
