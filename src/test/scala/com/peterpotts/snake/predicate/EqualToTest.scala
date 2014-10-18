package com.peterpotts.snake.predicate

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class EqualToTest extends WordSpec with Matchers {
  "An equal to predicate" should {
    "match to same event name" in {
      val int = Random.nextInt()
      val extractor: Extractor[Int] = _.toString
      EqualTo(extractor, int)(int) should equal(true)
    }

    "not match to different event name" in {
      val int = Random.nextInt()
      val extractor: Extractor[Int] = _ * 2
      EqualTo(extractor, int)(int) should equal(false)
    }
  }
}

