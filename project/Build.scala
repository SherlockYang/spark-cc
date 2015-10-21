import sbt._
import Keys._
import java.io.File

object BuildSparkCC extends Build {
  val Organization = "cn.edu.tsinghua.keg"
  val Name = "spark-cc"
  val Version = "0.1"
  val ScalaVersion = "2.11.7"

  lazy val root = Project(id = Name, base = file("."))
    .settings(
      sourcesInBase := false,
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers ++= Seq(
        Classpaths.typesafeReleases),
      //scalacOptions := Seq("-deprecation", "-language:postfixOps", "-feature", "-Yinline-warnings"),
      libraryDependencies ++= Seq(
        "org.apache.spark" % "spark-core_2.11" % "1.5.1",
        "org.apache.spark" % "spark-graphx_2.11" % "1.5.1"
  ) //,
    )
}

