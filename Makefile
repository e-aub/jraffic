BASE_DIR := $(shell pwd)

.PHONY: clean dependencies javac java help

clean:
	@echo "Cleaning build artifacts..."
	rm -rf out
	rm -rf lib

dependencies:
	@echo "[1/4] Creating lib folder..."
	mkdir -p lib

	@echo "[2/4] Downloading core.jar directly..."
	wget https://repo1.maven.org/maven2/org/processing/core/4.4.6/core-4.4.6.jar -O lib/core.jar

	@echo "[3/4] Verifying core.jar..."
	if [ ! -f lib/core.jar ]; then \
		echo "ERROR: core.jar not found!"; \
		exit 1; \
	fi

	@echo "[4/4] Done! core.jar is now in $(BASE_DIR)/lib/"
	ls -l lib/core.jar

javac: dependencies
	@echo "Compiling sources..."
	mkdir -p out
	javac -cp lib/core.jar src/*.java -d out

java: javac
	@echo "Running Jraffic..."
	java -cp "lib/core.jar:out" Jraffic

help:
	@echo "Available targets:"
	@echo "  clean        - Remove build artifacts"
	@echo "  dependencies - Download core.jar into lib/"
	@echo "  javac        - Compile Java files with core.jar"
	@echo "  java         - Run Jraffic"
