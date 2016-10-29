lazy val commonSettings = Seq(
  organization       := "com.github.aoiroaoino",
  scalaVersion       := "2.11.8",
  crossScalaVersions := Seq("2.10.6", "2.11.8"),
  scalacOptions     ++= Seq(
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

val json4sVersion  = "3.2.11"
val monocleVersion = "1.3.1"

// main
lazy val json4sNative = "org.json4s"                 %% "json4s-native" % json4sVersion
lazy val monocleCore  = "com.github.julien-truffaut" %% "monocle-core"  % monocleVersion
lazy val monocleMacro = "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion
// test
lazy val scalaTest    = "org.scalatest" %% "scalatest" % "2.2.6" % "test"

lazy val root = (project in file("."))
  .settings(moduleName := "json4s-optics")
  .settings(commonSettings)
  .settings(libraryDependencies ++= Seq(
    json4sNative,
    monocleCore,
    monocleMacro,
    scalaTest
  ))
