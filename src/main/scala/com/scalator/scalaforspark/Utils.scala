package com.scalator.scalaforspark

import com.typesafe.config.Config
import org.apache.spark.sql.SparkSession

object Utils {

  def getOrCreateSparkSession(conf: Config) = {

    val appName = "Scala for Spark"
    val master = conf.getString("spark.master")
    // val jars = conf.getString("spark.jars.location")
    val session = SparkSession.builder
      .master(master)
      .appName(appName)
      // .config("spark.jars", jars)
      .getOrCreate()
    session
  }
}
