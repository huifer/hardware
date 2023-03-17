#!/bin/bash
port=9101

if netstat -tuln | grep -q :$port ; then
     pid=$(netstat -nlp | grep -w ":$port" | awk '{print $7}' | awk -F"/" '{print $1}' | tr '\n' ' ')
      echo  " $(date +%F%n%T)  $port 端口存在，对应的PID为：$pid"
else
   # 写启动脚本
   echo "$(date +%F%n%T) 发现$port死亡重启 "
   sh /root/start/lxs-main-start.sh restart
fi