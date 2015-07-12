echo build package!
call mvn clean package -Dmaven.test.skip=true
@echo off

java -jar target\tea-1.0.1.jar