package com.peterpotts.snake.coercion

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class LongCoercerTest extends WordSpec with Matchers {
  "Long coercer" should {
    "evaluate long to long" in {
      val long = Random.nextLong()
      LongCoercer(long) should equal(long)
    }

    "evaluate number to long" in {
      val double = Random.nextDouble()
      LongCoercer(double) should equal(math.round(double))
    }

    "evaluate string to long" in {
      val string = Random.nextLong().toString
      LongCoercer(string) should equal(string.toLong)
    }
  }
}

