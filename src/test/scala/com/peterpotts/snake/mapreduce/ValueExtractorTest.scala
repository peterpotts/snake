package com.peterpotts.snake.mapreduce

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class ValueExtractorTest extends WordSpec with Matchers {
  "A value extractor" should {
    "always return value" in {
      val value = Random.nextInt()
      val any = if (Random.nextBoolean()) Random.nextInt() else Random.nextString(4)
      ValueExtractor(value)(any) should equal(value)
    }
  }
}

