package com.peterpotts.snake.mapreduce

import com.peterpotts.snake.predicate.Extractor
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class ExtractorMapTest extends WordSpec with Matchers {
  "An extractor map function" should {
    "extract keys and values" in {
      val value = Random.nextInt()
      val name = Random.nextString(4)
      val extractor1: Extractor[Int] = _.toString
      val extractor2: Extractor[Int] = _ * 2
      val target = ExtractorMap(Map(name -> extractor1), extractor2)
      target(value) should equal(Traversable(Map(name -> value.toString) -> value * 2))
    }
  }
}
