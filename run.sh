#!/bin/bash
PID=`sudo lsof -t -i:4041`

if [[ -z "$PID" ]] then
Kill -9 PID
fi

java -jar ./build/libs/*.jar -role hub &