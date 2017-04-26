package com.scalator.scalaforspark

import org.scalatest.{Matchers, WordSpecLike}

class Exercise2Spec
  extends WordSpecLike
      with Matchers {


    "Exercise 2" should {

      "`compose` should be successfully implemented" in {

        assert(Exercises.compose(_ + 1, _ + 2, 0) == 3)
        assert(Exercises.compose(_ + 0, _ + 0, 0) == 0)
        assert(Exercises.compose(_ * 2, _ / 2, 10) == 10)

      }
    }
  }
