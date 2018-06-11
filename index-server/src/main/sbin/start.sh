#!/usr/bin/env bash

now=`date +%Y-%m-%d`
[ -d logs ] || mkdir logs
nohup java -cp conf/:index-server-0.1.0.jar org.springframework.boot.loader.JarLauncher 1>/dev/null 2>error_${now}.log &