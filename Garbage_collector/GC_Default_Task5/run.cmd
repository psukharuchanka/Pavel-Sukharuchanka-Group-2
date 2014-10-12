SET JAVA_OPTS=-Xms2m -Xmx18m -Xmn1m -XX:PermSize=24m -XX:MaxPermSize=36m -XX:+UseConcMarkSweepGC -XX:ParallelCMSThreads=2
start "Concurrent Mark Sweep (CMS) Collector with 2 Parallel CMS Threads" run-jar.cmd d:\Epam\EclipseWorkspace\JMPTasks\target JMPTasks-0.0.1-SNAPSHOT.jar
exit