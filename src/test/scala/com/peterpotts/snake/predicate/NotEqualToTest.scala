package com.peterpotts.snake.predicate

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class NotEqualToTest extends WordSpec with Matchers {
  "A not equal to predicate" should {
    "not match to same event name" in {
      val int = Random.nextInt()
      val extractor: Extractor[Int] = _.toString
      NotEqualTo(extractor, int)(int) should equal(false)
    }

    "match to different event name" in {
      val int = Random.nextInt()
      val extractor: Extractor[Int] = _ * 2
      NotEqualTo(extractor, int)(int) should equal(true)
    }
  }
}

