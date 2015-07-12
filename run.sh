#!/bin/bash
mvn_bin=`which mvn`
echo "1. begin build package"
$mvn_bin clean package -Dmaven.test.skip=true

echo "2. begin generator files"
basePath=`pwd`
if [[ $1 ]]; then
	$basePath = $1
fi
java -jar target/tea-1.0.1.jar

