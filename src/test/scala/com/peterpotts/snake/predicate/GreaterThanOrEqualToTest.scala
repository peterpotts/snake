package com.peterpotts.snake.predicate

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class GreaterThanOrEqualToTest extends WordSpec with Matchers {
  "A greater than or equal to predicate" should {
    "match a higher int" in {
      val lower = -Random.nextInt(10)
      val higher = Random.nextInt(10)
      val extractor: Extractor[Int] = _ / 2
      val target = GreaterThanOrEqualTo(extractor, lower)
      target(higher) should equal(true)
    }

    "not match a lower int" in {
      val lower = -Random.nextInt(10)
      val higher = Random.nextInt(10)
      val extractor: Extractor[Int] = _ / 2
      val target = GreaterThanOrEqualTo(extractor, higher)
      target(lower) should equal(false)
    }
  }
}
