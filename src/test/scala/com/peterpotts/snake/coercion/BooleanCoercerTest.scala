package com.peterpotts.snake.coercion

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class BooleanCoercerTest extends WordSpec with Matchers {
  "Boolean coercer" should {
    "evaluate boolean to boolean" in {
      val boolean = Random.nextBoolean()
      BooleanCoercer(boolean) should equal(boolean)
    }

    "evaluate number to boolean" in {
      val int = Random.nextInt()
      BooleanCoercer(int) should equal(int != 0)
    }

    "evaluate string to boolean" in {
      val string = Random.nextBoolean().toString
      BooleanCoercer(string) should equal(string.toBoolean)
    }
  }
}

