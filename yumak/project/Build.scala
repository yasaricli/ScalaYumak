import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "scala-yumak"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
      jdbc,
      anorm,
      "postgresql" % "postgresql" % "8.4-702.jdbc4"
      // "mysql" % "mysql-connector-java" % "5.1.20"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(

    // Settings base directory add uploads 
    playAssetsDirectories <+= baseDirectory / "uploads"
  )

}
