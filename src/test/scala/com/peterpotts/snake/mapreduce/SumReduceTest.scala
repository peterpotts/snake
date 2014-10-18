package com.peterpotts.snake.mapreduce

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class SumReduceTest extends WordSpec with Matchers {
  "A sum reduce function" should {
    "get the sum of the values" in {
      val key = Random.nextString(4)
      val values = Traversable(Random.nextDouble(), Random.nextDouble(), Random.nextDouble(), Random.nextDouble())
      val target = SumReduce
      target(key, values) should equal(values.sum)
    }
  }
}
