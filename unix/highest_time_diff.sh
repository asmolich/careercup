#! /bin/bash

cat log.log | sed s/STARTTIME:/start,/ | sed 'N;s/\nENDTIME:/,end,/' | sed '/-----/ d' | awk -F, '{print $4"- "$2}'
