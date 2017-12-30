name := "En_Rule_Engine"

version := "0.1"

scalaVersion := "2.10.6"

libraryDependencies ++= {
  val scalaTestVersion = "3.0.4"
  val yamlVersion = "1.19"

  Seq(
    "org.scalactic" %% "scalactic" % scalaTestVersion % "test",
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
    "org.yaml" % "snakeyaml" % yamlVersion
  )
}