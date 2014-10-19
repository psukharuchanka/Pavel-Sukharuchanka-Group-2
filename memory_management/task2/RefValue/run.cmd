SET JAVA_OPTS=-Xss1m -Xms512m -Xmn256m -XX:SurvivorRatio=3
start "Memory Eater" run-jar.cmd d:\Epam\EclipseWorkspace\RefValue\target RefValue-0.0.1-SNAPSHOT.jar
exit