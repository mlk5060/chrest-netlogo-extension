#!/bin/bash
CHREST_JAR_LOCATION=/home/martyn/chrest/target/
CHREST_JAR=$(tree -fi "$CHREST_JAR_LOCATION" | grep "\.jar" | awk -F "/" '{print $NF}')

cp ${CHREST_JAR_LOCATION}/${CHREST_JAR} .

tree -fi src/ | grep "\.java" > sources.txt

javac -classpath /home/martyn/netlogo-5.1.0/NetLogo.jar:/home/martyn/netlogo-5.1.0/extensions/chrest/${CHREST_JAR} -d classes @sources.txt
jar cfm chrest.jar manifest.txt -C classes .