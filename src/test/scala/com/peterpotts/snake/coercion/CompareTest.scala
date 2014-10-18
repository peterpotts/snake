package com.peterpotts.snake.coercion

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class CompareTest extends WordSpec with Matchers {
  "Compare" should {
    "coerce types" in {
      val int = Random.nextInt(2001) - 1000
      Compare(int.toDouble, int.toString) should equal(0)
    }
  }
}
