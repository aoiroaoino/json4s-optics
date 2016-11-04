organization := "com.github.aoiroaoino"

name := "json4s-optics"

description := "Providing optics operation for Json4s by Monocle."

scalaVersion := "2.11.8"

crossScalaVersions := Seq("2.11.8", "2.12.0")

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

val monocleVersion = "1.3.2"

libraryDependencies ++= Seq(
  // main
  "org.json4s"                 %% "json4s-native" % "3.5.0",
  "com.github.julien-truffaut" %% "monocle-core"  % monocleVersion,
  "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion,
  // test
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)
