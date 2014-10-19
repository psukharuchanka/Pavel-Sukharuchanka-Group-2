SET JAVA_OPTS=-XX:MaxPermSize=1m -Xmx512m
start "Permgen out of memory" run-jar.cmd d:\Epam\EclipseWorkspace\PermGenTest\target PermGenTest-0.0.1-SNAPSHOT.jar
exit