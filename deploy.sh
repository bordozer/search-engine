#!/bin/bash

./gradlew clean build -x check && java -ea -Dspring.profiles.active=dev -jar build/libs/search-engine-1.00.jar
