package com.peterpotts.snake.predicate

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class FalseTest extends WordSpec with Matchers {
  "The false predicate" should {
    "evaluate everything to false" in {
      False(Random.nextInt()) should equal(false)
    }
  }
}
