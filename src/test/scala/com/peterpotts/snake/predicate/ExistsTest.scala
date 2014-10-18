package com.peterpotts.snake.predicate

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class ExistsTest extends WordSpec with Matchers {
  "An exists predicate" should {
    "match existence" in {
      val value = Random.nextInt()
      val extractor: Extractor[Int] = _ * 2
      val target = Exists(extractor, sense = true)
      target(value) should equal(true)
    }

    "not match non existence" in {
      val value = Random.nextInt()
      val extractor: Extractor[Int] = _ => throw new NoSuchElementException
      val target = Exists(extractor, sense = false)
      target(value) should equal(true)
    }
  }
}
