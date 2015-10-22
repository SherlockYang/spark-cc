import sbt._
import sbt.Keys._
import sbtsparkpackage.SparkPackagePlugin
import sbtsparkpackage.SparkPackagePlugin.autoImport._

object BuildSparkCC extends Build {
  val Organization = "cn.edu.tsinghua.keg"
  val Name = "spark-cc"
  val Version = "0.1"
  val ScalaVersion = "2.10.4"

  lazy val root = Project(id = Name, base = file("."))
    .settings(
      sourcesInBase := false,
      sbtPlugin := true,
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
      sparkComponents += "graphx",
      spName := "sherlockyang/spark-cc",
      sparkVersion := "1.5.1",
      credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
      libraryDependencies ++= Seq(
        //"org.apache.spark" % "spark-core_2.11" % "1.5.1",
        //"org.apache.spark" % "spark-graphx_2.11" % "1.5.1"
      ),
      dependencyOverrides ++= Set(
        //"org.apache.spark" % "spark-core_2.11" % "1.5.1",
        //"org.json4s" % "json4s-native_2.11" % "3.3.0",
        //"org.json4s" % "json4s-ast_2.11" % "3.3.0",
        //"org.json4s" % "json4s-core_2.11" % "3.3.0"
      )
    )
}

