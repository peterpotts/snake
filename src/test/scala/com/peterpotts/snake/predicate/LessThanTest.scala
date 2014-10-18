package com.peterpotts.snake.predicate

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class LessThanTest extends WordSpec with Matchers {
  "A less than predicate" should {
    "not match a higher int" in {
      val lower = -Random.nextInt(10)
      val higher = 1 + Random.nextInt(10)
      val extractor: Extractor[Int] = _ / 2
      val target = LessThan(extractor, lower)
      target(higher) should equal(false)
    }

    "match a lower int" in {
      val lower = -Random.nextInt(10)
      val higher = 1 + Random.nextInt(10)
      val extractor: Extractor[Int] = _ / 2
      val target = LessThan(extractor, higher)
      target(lower) should equal(true)
    }
  }
}
