import sbt._
import Keys._

object ProjectBuild extends Build {

  val defaultSettings = Seq(
    scalaVersion        := "2.11.7",
    crossScalaVersions  := Seq("2.10.6", "2.11.7"),
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
  val monocleVersion      = "1.2.0"

  // main libraries
  lazy val json4s       = "org.json4s"                 %% "json4s-native" % json4sVersion
  lazy val monocleCore  = "com.github.julien-truffaut" %% "monocle-core"  % monocleVersion
  lazy val monocleMacro = "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion

  // test libraries
  lazy val scalaTest    = "org.scalatest"              %% "scalatest"     % "2.2.4"         % "test"

  lazy val root = Project(
    id       = "json4s-optics",
    base     = file("."),
    settings = defaultSettings,
    aggregate    = Seq(core, test),
    dependencies = Seq(core, test)
  )

  lazy val core = Project(
    id       = "core",
    base     = file("core"),
    settings = defaultSettings ++ Seq(
      name := "json4s-optics-core",
      libraryDependencies ++= Seq(json4s, monocleCore, monocleMacro)
    )
  )

  lazy val test = Project(
    id       = "test",
    base     = file("test"),
    settings = defaultSettings ++ Seq(
      name := "json4s-optics-test",
      libraryDependencies ++= Seq(json4s, monocleCore, monocleMacro, scalaTest)
    ),
    dependencies = Seq(core)
  )
}
