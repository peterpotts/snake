package com.peterpotts.snake

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Random

@RunWith(classOf[JUnitRunner])
class SeqSnakeTest extends WordSpec with Matchers {
  "A sequence snake" should {
    "flatten" in {
      val expected = Seq(Random.nextInt(), Random.nextInt(), Random.nextInt(), Random.nextInt())
      val target = new SeqSnake(expected)
      val actual = Await.result(target.continuation.flatten, 1.second)
      actual should equal(expected)
    }

    "query" in {
      val seq = Seq(Random.nextInt(), Random.nextInt(), Random.nextInt(), Random.nextInt())
      val target = new SeqSnake(seq).filter(_ > 0).sorted.drop(1).take(2)
      val actual = Await.result(target.continuation.flatten, 1.second)
      val expected = seq.filter(_ > 0).sorted.drop(1).take(2)
      actual should equal(expected)
    }
  }
}

