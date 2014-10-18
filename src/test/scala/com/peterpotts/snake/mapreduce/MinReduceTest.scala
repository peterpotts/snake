package com.peterpotts.snake.mapreduce

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class MinReduceTest extends WordSpec with Matchers {
  "A minimum reduce function" should {
    "get the minimum of the values" in {
      val key = Random.nextString(4)
      val values = Traversable(Random.nextDouble(), Random.nextDouble(), Random.nextDouble(), Random.nextDouble())
      val target = MinReduce
      target(key, values) should equal(values.min)
    }
  }
}
