SET JAVA_OPTS=-Xms4m -Xmx16m -Xmn2m -XX:PermSize=12m -XX:MaxPermSize=18m -XX:+UseG1GC
start "G1 Garbage Collector" run-jar.cmd d:\Epam\EclipseWorkspace\JMPTasks\target JMPTasks-0.0.1-SNAPSHOT.jar
exit