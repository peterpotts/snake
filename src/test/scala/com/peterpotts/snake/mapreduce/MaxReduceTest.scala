package com.peterpotts.snake.mapreduce

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class MaxReduceTest extends WordSpec with Matchers {
  "A maximum reduce function" should {
    "get the maximum of the values" in {
      val key = Random.nextString(4)
      val values = Traversable(Random.nextDouble(), Random.nextDouble(), Random.nextDouble(), Random.nextDouble())
      val target = MaxReduce
      target(key, values) should equal(values.max)
    }
  }
}
