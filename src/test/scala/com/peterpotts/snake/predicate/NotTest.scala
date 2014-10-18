package com.peterpotts.snake.predicate

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class NotTest extends WordSpec with Matchers {
  "The not predicate" should {
    "evaluate not false to true" in {
      Not[Int](False)(Random.nextInt()) should equal(true)
    }

    "evaluate not true to false" in {
      Not[Int](True)(Random.nextInt()) should equal(false)
    }
  }
}
