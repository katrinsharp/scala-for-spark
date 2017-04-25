package com.scalator.scalaforspark

import com.typesafe.config.ConfigFactory

object Main extends App {

  val conf = ConfigFactory.load()
  val sparkSession = Utils.getOrCreateSparkSession(conf)

  //FIXME: add real job functionality

}
