dir /s /B src\*.java > sources.txt
javac @sources.txt
java -cp src Main
pause