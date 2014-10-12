SET JAVA_OPTS=-Xms9m -Xmx18m -Xmn3m -XX:PermSize=40m -XX:MaxPermSize=40m -XX:+UseParallelOldGC
start "Parallel Old Collector" run-jar.cmd d:\Epam\EclipseWorkspace\JMPTasks\target JMPTasks-0.0.1-SNAPSHOT.jar
exit