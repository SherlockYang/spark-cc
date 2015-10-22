import sbtsparkpackage.SparkPackagePlugin.autoImport.defaultSPSettings

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }


