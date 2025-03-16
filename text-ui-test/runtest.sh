#!/usr/bin/env bash

# Change to script directory
cd "${0%/*}"

cd ..
./gradlew clean shadowJar

cd out/artifacts/tp_main_jar

# Find the .jar file
JARLOC=$(find . -name "*.jar" -print -quit)

cd ../../text-ui-test

java -jar "$JARLOC" < input.txt > ACTUAL.TXT

# Convert EXPECTED.TXT to UNIX format and compare with ACTUAL.TXT
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix EXPECTED-UNIX.TXT ACTUAL.TXT
diff EXPECTED-UNIX.TXT ACTUAL.TXT

if [ $? -eq 0 ]; then
    echo "Test passed!"
    exit 0
else
    echo "Test failed!"
    exit 1
fi
