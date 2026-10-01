@echo off
echo Compiling Java files...

echo Compiling shared utilities...
javac shared\*.java

echo Compiling servers...
javac -cp . server\*.java

echo Compiling clients...
javac -cp . client\*.java

echo Compiling utilities...
javac -cp . *.java

echo Compilation complete!
echo.
echo Available programs:
echo - run-server1.bat    (Start Server 1)
echo - run-server2.bat    (Start Server 2) 
echo - run-client.bat     (Console Client)
echo - run-gui-client.bat (GUI Client)
echo.
pause