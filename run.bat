dir /B *.java > sources.txt
javac @sources.txt
java Main
pause