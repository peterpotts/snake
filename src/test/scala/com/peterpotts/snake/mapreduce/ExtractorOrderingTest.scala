package com.peterpotts.snake.mapreduce

import com.peterpotts.snake.predicate.Extractor
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class ExtractorOrderingTest extends WordSpec with Matchers {
  "An extractor ordering" should {
    "order according to the extracted value" in {
      val value1 = Random.nextInt()
      val value2 = Random.nextInt()
      val extractor: Extractor[Int] = _.toString
      val actual = ExtractorOrdering(extractor).compare(value1, value2)
      val expected = value1 compare value2
      actual should equal(expected)
    }
  }
}

