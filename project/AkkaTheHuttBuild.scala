import sbt._
import sbt.Keys._

object AkkaTheHuttBuild extends Build {

  lazy val akkaTheHutt = Project(
    id = "akkaTheHutt",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "akkaTheHutt",
      organization := "org.superfluo",
      version := "0.0.1",
      
      scalaVersion := "2.11.0",
      crossScalaVersions  := Seq("2.11.0", "2.10.3"),

      scalacOptions += "-feature",
      scalacOptions += "-deprecation",

      resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases",
      resolvers += "Sonatype OSS Releases" at
        "http://oss.sonatype.org/content/repositories/releases/",

      libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.2",

      libraryDependencies += "joda-time" % "joda-time" % "2.1",
      libraryDependencies += "org.joda" % "joda-convert" % "1.2",

      libraryDependencies += "com.typesafe.akka" %% "akka-persistence-experimental" % "2.3.2",

      // test
      libraryDependencies += "junit" % "junit" % "4.11" % "test",
      libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.5" % "test",
      libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.3.2" % "test",
      libraryDependencies += "org.mockito" % "mockito-core" % "1.9.5" % "test"

    )
  )
}
