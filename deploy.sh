#!/bin/bash

./gradlew clean build -x check && java -ea -XX:+UseG1GC -Dspring.profiles.active=dev -jar build/libs/search-engine-1.00.jar
#./gradlew clean build -x check && java -agentlib:tijmp -ea –XX:+UseG1GC -Dspring.profiles.active=dev -jar build/libs/search-engine-1.00.jar
#./gradlew clean build -x check && java -agentpath:tijmp -ea –XX:+UseG1GC -Dspring.profiles.active=dev -jar build/libs/search-engine-1.00.jar
