//
//val refinedsVersion = "0.9.15"
//val aws3sVersion = "1.9.0"
//val circeVersion = "0.14.1"
//val monocleVersion = "2.0.3"
//val scalatestVersion = "3.2.12"
//val catsVersion = "2.7.0"
//val logbackVersion = "1.2.11"
//val akkaVersion = "2.6.19"
//
//libraryDependencies ++= Seq(
//  // testing
//  "org.scalatest" %% "scalatest" % scalatestVersion,
//  "org.scalatest" %% "scalatest" % scalatestVersion % "test",
//
//  // Apache Commons
//  "commons-io" % "commons-io" % "2.6",
//  // akka streams
////  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
////  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
//  // akka logging
//  "ch.qos.logback" % "logback-classic" % logbackVersion,
//
//  // cats
//  "org.typelevel" %% "cats-core" % catsVersion,
//
//  // circe
//  "io.circe" %% "circe-core" % circeVersion,
//  "io.circe" %% "circe-generic" % circeVersion,
//  "io.circe" %% "circe-parser" % circeVersion,
//
//  // akka http + serializers
//  //"com.typesafe.akka" % "akka-http" % akkaVersion,
//
//
//
//)
ThisBuild / version := "1.0"
ThisBuild / scalaVersion := "2.13.8"
libraryDependencies += "org.typelevel" %% "cats-core" % "2.2.0"