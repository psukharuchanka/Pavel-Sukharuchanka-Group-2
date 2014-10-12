SET JAVA_OPTS=-Xms4m -Xmx16m -Xmn3m -XX:PermSize=24m -XX:MaxPermSize=32m -XX:+UseParallelGC -XX:ParallelGCThreads=2
start "Parallel Collector with 2 Parallel CMS Threads" run-jar.cmd d:\Epam\EclipseWorkspace\JMPTasks\target JMPTasks-0.0.1-SNAPSHOT.jar
exit