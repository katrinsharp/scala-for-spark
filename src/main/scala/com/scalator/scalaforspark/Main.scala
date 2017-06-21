package com.scalator.scalaforspark

import com.typesafe.config.ConfigFactory

object Main extends App {

  val conf = ConfigFactory.load()
  val sparkSession = Utils.getOrCreateSparkSession(conf)
  val sc = sparkSession.sparkContext

  val inputFileName = "test-data/Quick_Start-Spark_2_1_1_Documentation.htm"
  val inputRDD = sc.textFile(inputFileName, 4)

  val sparkMentions = inputRDD.filter(x => x.contains("spark") || x.contains("Spark")).count()

  println("*"*20)
  println(s"Word Spark is mentioned in: $sparkMentions")
  println("*"*20)

  sc.stop()

}
