#!/bin/bash

# WORKING ON THIS: SHOULD BE A SCRIPT THAT WILL MAKE BUILDING THE CHREST NETLOGO EXTENSION AND EXPORTING IT TO THE CHREST TILEWORLD NETLOGO MODEL A ONE-STEP INVOCATION.

if [[ -e ./sources.txt ]]; then
	rm ./sources.txt
fi

shopt -s nullglob
set -- chrest-*.jar
if [ "$#" -gt 0 ]; then
  rm "$@"
fi

tree -fi src/ | grep ".java" > sources.txt

while [[ ! -e ./sources.txt ]]; do
	sleep 1
done

javac -classpath /home/martyn/netlogo-5.1.0/NetLogo.jar:/home/martyn/netlogo-5.1.0/extensions/chrest/chrest-5-beta.jar -d classes @sources.txt
jar cfm chrest.jar manifest.txt -C classes .

while [[ ! -e ./chrest.jar ]]; do
	sleep 1
done

cp {chrest.jar,chrest-5-beta.jar} /home/martyn/netlogo-5.1.0/models/MyModels/CHRESTTileworld/chrest/
