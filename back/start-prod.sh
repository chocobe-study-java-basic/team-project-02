#!/bin/bash
## 배포환경의 서버(JAR) 실행 파일

JAR=budzet-0.0.1-SNAPSHOT.jar
LOG=budzet.log

source ./env.sh

nohup java -jar "$JAR" > "$LOG" 2>&1 &