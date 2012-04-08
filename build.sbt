/** Project */
name := "scala-jersey-example"

version := "1.0-SNAPSHOT"

organization := "org.coomber"

scalaVersion := "2.9.1"

/** Dependencies */
resolvers ++= Seq("Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                  "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases",
                  "javanetDeps" at "http://download.java.net/maven/2/")

seq(webSettings :_*)

libraryDependencies ++= Seq(
  "com.sun.jersey" % "jersey-core" % "1.8",
  "com.sun.jersey" % "jersey-server" % "1.8",
  "com.sun.jersey" % "jersey-json" % "1.8",
  "com.sun.jersey.contribs" % "jersey-multipart" % "1.8",
  "com.sun.jersey.contribs" % "jersey-spring" % "1.8",
  "org.eclipse.jetty" % "jetty-webapp" % "8.0.1.v20110908" % "container",
  "org.springframework" % "spring-core" % "3.0.5.RELEASE",
  "org.springframework" % "spring-context" % "3.0.5.RELEASE",
  "org.springframework" % "spring-web" % "3.0.5.RELEASE",
  "org.springframework" % "spring-test" % "3.0.5.RELEASE" % "test",
  "javax.servlet" % "servlet-api" % "2.5" % "provided",
  "org.slf4j" % "slf4j-api" % "1.6.1",
  "ch.qos.logback" % "logback-classic" % "0.9.28",
  "commons-io" % "commons-io" % "2.1",
  "com.hazelcast" % "hazelcast" % "2.0.2",
  "com.sun.jersey.jersey-test-framework" % "jersey-test-framework-core" % "1.8" % "test",
  "com.sun.jersey.jersey-test-framework" % "jersey-test-framework-inmemory" % "1.8" % "test",  
  "org.eclipse.jetty" % "jetty-server" % "7.0.1.v20091125" % "test",
  "org.eclipse.jetty" % "jetty-webapp" % "7.0.1.v20091125" % "test",
  "com.sun.jersey" % "jersey-client" % "1.8" % "test"   
)

/** Compilation */
scalacOptions += "-deprecation"

javaOptions += "-Xss4M"

/** Console */
initialCommands in console := "import org.specs2._"

/* JUnit support */
libraryDependencies += "junit" % "junit" % "4.10" % "test"

libraryDependencies += "com.novocode" % "junit-interface" % "0.8" % "test->default"
