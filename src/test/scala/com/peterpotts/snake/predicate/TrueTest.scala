package com.peterpotts.snake.predicate

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class TrueTest extends WordSpec with Matchers {
  "The true predicate" should {
    "evaluate everything to true" in {
      True(Random.nextInt()) should equal(true)
    }
  }
}
