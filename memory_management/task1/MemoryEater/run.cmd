SET JAVA_OPTS=-Xss2m -Xmx1024m -XX:SurvivorRatio=5
start "Memory Eater" run-jar.cmd d:\Epam\EclipseWorkspace\MemoryEater\target MemoryEater-0.0.1-SNAPSHOT.jar
exit