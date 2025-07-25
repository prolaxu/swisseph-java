#!/bin/bash

# Swiss Ephemeris Native Library Build Script
# This script compiles the C source code into a shared library

set -e

echo "Building Swiss Ephemeris native library..."

# Define directories
NATIVE_SRC_DIR="src/main/native"
NATIVE_OUTPUT_DIR="src/main/native"

# Find JDK include paths dynamically
if [ -z "$JAVA_HOME" ]; then
    JAVA_HOME=$(dirname $(dirname $(readlink -f $(which javac))))
fi

JNI_INCLUDE="-I${JAVA_HOME}/include"

# Add platform-specific include paths
if [[ "$OSTYPE" == "linux-gnu"* ]]; then
    JNI_INCLUDE="$JNI_INCLUDE -I${JAVA_HOME}/include/linux"
    LIB_NAME="libswisseph.so"
elif [[ "$OSTYPE" == "darwin"* ]]; then
    JNI_INCLUDE="$JNI_INCLUDE -I${JAVA_HOME}/include/darwin"
    LIB_NAME="libswisseph.dylib"
elif [[ "$OSTYPE" == "cygwin" ]] || [[ "$OSTYPE" == "msys" ]] || [[ "$OSTYPE" == "win32" ]]; then
    JNI_INCLUDE="$JNI_INCLUDE -I${JAVA_HOME}/include/win32"
    LIB_NAME="swisseph.dll"
else
    echo "Unsupported platform: $OSTYPE"
    exit 1
fi

# Clean previous build
rm -f ${NATIVE_OUTPUT_DIR}/${LIB_NAME}

# Compile C source code into a shared library
echo "Compiling C source files..."
echo "Using JNI includes: $JNI_INCLUDE"

# Find all C files except test/example files
C_FILES=$(find ${NATIVE_SRC_DIR} -name '*.c' | grep -v -E 'sweasp.c|sweclips.c|swemini.c|swephgen4.c|swetest.c|swevents.c')

echo "Compiling files: $C_FILES"

if [[ "$OSTYPE" == "darwin"* ]]; then
    # macOS compilation
    gcc -shared -fPIC ${JNI_INCLUDE} \
        -include ${NATIVE_SRC_DIR}/swephexp.h \
        -o ${NATIVE_OUTPUT_DIR}/${LIB_NAME} \
        $C_FILES
else
    # Linux/Windows compilation
    gcc -shared -fPIC ${JNI_INCLUDE} \
        -include ${NATIVE_SRC_DIR}/swephexp.h \
        -o ${NATIVE_OUTPUT_DIR}/${LIB_NAME} \
        $C_FILES
fi

echo "Native library compiled successfully: ${LIB_NAME}"
echo "Location: ${NATIVE_OUTPUT_DIR}/${LIB_NAME}"
