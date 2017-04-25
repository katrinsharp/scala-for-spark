package com.scalator.scalaforspark

import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.io.Source

class HelloLocalSparkSpec
  extends WordSpecLike
      with Matchers
      with BeforeAndAfterAll {

    val conf = ConfigFactory.load()

    lazy val sparkSession = Utils.getOrCreateSparkSession(conf)

    override protected def afterAll(): Unit = {
      super.afterAll()
      sparkSession.stop()
    }

    "Hello Spark" should {

      val sc = sparkSession.sparkContext
      val myJob = new MyJob(sc)

      "successfully creates RDD from Scala collection" in {

        assert(myJob.createRDD(10).count() == 10)

      }
      "successfully creates RDD from file" in {

        val filePath = "src/test/resources/createRDDFromFile.csv"
        assert(myJob.createRDDFromFile(filePath).collect() sameElements Source.fromFile(filePath).getLines().toList)
      }
    }
  }
