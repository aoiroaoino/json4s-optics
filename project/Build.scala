import sbt._
import Keys._

object ProjectBuild extends Build {

  val defaultSettings = Seq(
    scalaVersion        := "2.11.7",
    crossScalaVersions  := Seq("2.10.5", "2.11.7"),
    scalacOptions      ++= Seq(
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

  val json4sVersion       = "3.2.11"
  val monocleVersion      = "1.2.0-M1"

  // main libraries
  lazy val json4s       = "org.json4s"                 %% "json4s-native" % json4sVersion
  lazy val monocleCore  = "com.github.julien-truffaut" %% "monocle-core"  % monocleVersion
  lazy val monocleMacro = "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion

  // test libraries
  lazy val scalaTest    = "org.scalatest"              %% "scalatest"     % "2.2.1"         % "test"

  lazy val core = Project(
    "json4s-optics",
    file("."),
    settings = defaultSettings ++ Seq(
      libraryDependencies ++= Seq(json4s, monocleCore, monocleMacro, scalaTest)
    )
  )
}
