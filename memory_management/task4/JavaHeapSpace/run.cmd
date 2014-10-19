SET JAVA_OPTS=-XX:MaxPermSize=1m -Xmx10m
start "Heap out of memory" run-jar.cmd d:\Epam\EclipseWorkspace\JavaHeapSpace\target JavaHeapSpace-0.0.1-SNAPSHOT.jar
exit