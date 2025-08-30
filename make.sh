#!/bin/bash
set -e

# Use the current directory where the script is run
BASE_DIR="$(pwd)"
cd "$BASE_DIR"

echo "[1/4] Creating lib folder..."
mkdir -p lib

echo "[2/4] Downloading core.jar directly..."
wget https://repo1.maven.org/maven2/org/processing/core/4.4.6/core-4.4.6.jar -O lib/core.jar

echo "[3/4] Verifying core.jar..."
if [ ! -f lib/core.jar ]; then
    echo "ERROR: core.jar not found!"
    exit 1
fi

echo "[4/4] Done! core.jar is now in ${BASE_DIR}/lib/"
ls -l lib/core.jar