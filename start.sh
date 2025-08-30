#!/bin/bash
javac -cp lib/core.jar src/Jraffic.java -d out
java -cp "lib/core.jar:out" Jraffic