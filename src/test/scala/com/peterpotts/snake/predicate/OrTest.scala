package com.peterpotts.snake.predicate

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class OrTest extends WordSpec with Matchers {
  "The and predicate" should {
    "evaluate true or true to true" in {
      Or[Int](Seq(True, True))(Random.nextInt()) should equal(true)
    }

    "evaluate true or false to true" in {
      Or[Int](Seq(True, False))(Random.nextInt()) should equal(true)
    }

    "evaluate false or true to true" in {
      Or[Int](Seq(False, True))(Random.nextInt()) should equal(true)
    }

    "evaluate false or false to false" in {
      Or[Int](Seq(False, False))(Random.nextInt()) should equal(false)
    }
  }
}
