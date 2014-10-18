package com.peterpotts.snake.predicate

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class AndTest extends WordSpec with Matchers {
  "The and predicate" should {
    "evaluate true and true to true" in {
      And[Int](Seq(True, True))(Random.nextInt()) should equal(true)
    }

    "evaluate true and false to false" in {
      And[Int](Seq(True, False))(Random.nextInt()) should equal(false)
    }

    "evaluate false and true to false" in {
      And[Int](Seq(False, True))(Random.nextInt()) should equal(false)
    }

    "evaluate false and false to false" in {
      And[Int](Seq(False, False))(Random.nextInt()) should equal(false)
    }
  }
}
