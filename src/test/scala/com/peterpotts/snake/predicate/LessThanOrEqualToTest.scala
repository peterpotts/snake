package com.peterpotts.snake.predicate

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class LessThanOrEqualToTest extends WordSpec with Matchers {
  "A less than or equal to predicate" should {
    "not match a higher int" in {
      val lower = -Random.nextInt(10)
      val higher = Random.nextInt(10)
      val extractor: Extractor[Int] = _ / 2
      val target = LessThanOrEqualTo(extractor, lower)
      target(higher) should equal(false)
    }

    "match a lower int" in {
      val lower = -Random.nextInt(10)
      val higher = Random.nextInt(10)
      val extractor: Extractor[Int] = _ / 2
      val target = LessThanOrEqualTo(extractor, higher)
      target(lower) should equal(true)
    }
  }
}
