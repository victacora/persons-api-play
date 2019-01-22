name := """api-persons"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.8"

crossScalaVersions := Seq("2.11.12", "2.12.4")

libraryDependencies += guice
libraryDependencies += javaJpa
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.13"
libraryDependencies += "org.hibernate" % "hibernate-core" % "5.2.17.Final"

libraryDependencies += "net.jodah" % "failsafe" % "1.0.3"
libraryDependencies += "com.palominolabs.http" % "url-builder" % "1.1.0"

// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test

// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

PlayKeys.externalizeResources := false