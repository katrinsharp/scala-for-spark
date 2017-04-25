name := "scala-for-spark"

version := "1.0"

scalaVersion := "2.11.8"

val sparkVersion = "2.1.0"

libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc"         % "2.5.0" % "test",
  "org.scalikejdbc" %% "scalikejdbc-config"  % "2.5.0" % "test",
  "ch.vorburger.mariaDB4j" % "mariaDB4j" % "2.2.2" % "test",
  "com.typesafe" % "config" % "1.3.1",
  "mysql" % "mysql-connector-java" % "6.0.5" % "provided",
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql"  % sparkVersion % "provided")

resolvers += Resolver.sonatypeRepo("public")

javaOptions in Test ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", _ @ _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

fork in run := true
javaOptions in run ++= Seq(
  "-Dlog4j.configuration=log4j.properties")
fork in Test := true
javaOptions in Test ++= Seq(
  "-Dlog4j.configuration=log4j.properties")

run in Compile <<= Defaults.runTask(fullClasspath in Compile, mainClass in(Compile, run), runner in(Compile, run))
runMain in Compile <<= Defaults.runMainTask(fullClasspath in Compile, runner in(Compile, run))

initialCommands in console :=
  """
    |import com.typesafe.config.ConfigFactory
    |import com.scalator.scalaforspark._
    |val conf = ConfigFactory.parseString("spark.master=\"local[*]\"\nspark.jars.location=\"\"")
    |val sparkSession = Utils.getOrCreateSparkSession(conf)
    |val sc = sparkSession.sparkContext
  """.stripMargin
