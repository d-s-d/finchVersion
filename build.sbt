name := "finchVersion"
version := "0.1"
scalaVersion := "2.12.7"

val circeVersion = "0.10.+"
val finchVersion = "0.26.+"
val twitterVersion = "18.11.+"

libraryDependencies ++= Seq(
  "com.twitter" %% "twitter-server" % twitterVersion
)

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser",
).map(_ % circeVersion)

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finch-core",
  "com.github.finagle" %% "finch-circe",
).map(_ % finchVersion)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.+",
  "org.scalacheck" %% "scalacheck" % "1.13.+",
)
