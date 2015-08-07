import sbt._
import Keys._

object ProjectBuild extends Build {

  val projectScalaVersion = "2.11.7"
  val monocleVersion      = "1.1.1"
  val json4sVersion       = "3.2.11"

  val defaultSettings = Seq(
    version := "0.0.1-SNAPSHOT",
    scalaVersion := projectScalaVersion,
    libraryDependencies ++= Seq(
      "org.json4s"                 %% "json4s-native" % json4sVersion,
      "com.github.julien-truffaut" %% "monocle-core"  % monocleVersion,
      "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion,
      "org.scalatest"              %% "scalatest"     % "2.2.1"         % "test"
    ),
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-Xlint",
      "-Ywarn-dead-code",
      "-Ywarn-numeric-widen",
      "-language:higherKinds",
      "-language:postfixOps",
      "-language:implicitConversions"
    )
  )

  lazy val core = Project(
    "json4s-optics",
    file("."),
    settings = defaultSettings
  )
}
