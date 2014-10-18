package com.peterpotts.snake.coercion

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class DoubleCoercerTest extends WordSpec with Matchers {
  "Double coercer" should {
    "evaluate double to double" in {
      val double = Random.nextDouble()
      DoubleCoercer(double) should equal(double)
    }

    "evaluate number to double" in {
      val int = Random.nextInt()
      DoubleCoercer(int) should equal(int.toDouble)
    }

    "evaluate string to double" in {
      val string = Random.nextDouble().toString
      DoubleCoercer(string) should equal(string.toDouble)
    }
  }
}

