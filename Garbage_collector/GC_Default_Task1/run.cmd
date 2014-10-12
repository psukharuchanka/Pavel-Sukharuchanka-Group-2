SET JAVA_OPTS=-Xms6m -Xmx18m -Xmn2m -XX:PermSize=20m -XX:MaxPermSize=30m -XX:+UseSerialGC
start "Serial Collector" run-jar.cmd d:\Epam\EclipseWorkspace\JMPTasks\target JMPTasks-0.0.1-SNAPSHOT.jar
exit