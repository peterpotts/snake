package com.peterpotts.snake.coercion

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class IntCoercerTest extends WordSpec with Matchers {
  "Int coercer" should {
    "evaluate int to int" in {
      val int = Random.nextInt()
      IntCoercer(int) should equal(int)
    }

    "evaluate number to int" in {
      val double = Random.nextDouble()
      IntCoercer(double) should equal(math.round(double).toInt)
    }

    "evaluate string to int" in {
      val string = Random.nextInt().toString
      IntCoercer(string) should equal(string.toInt)
    }
  }
}
