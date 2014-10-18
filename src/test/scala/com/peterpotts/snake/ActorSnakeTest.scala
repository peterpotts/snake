package com.peterpotts.snake

import akka.actor.ActorSystem
import akka.testkit._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.concurrent.Await
import scala.util.Random

@RunWith(classOf[JUnitRunner])
class ActorSnakeTest
  extends TestKit(ActorSystem("ActorSnakeTest"))
  with WordSpecLike
  with Matchers
  with DefaultTimeout
  with BeforeAndAfterAll {
  override def afterAll() {
    TestKit.shutdownActorSystem(system)
  }

  implicit val executionContext = system.dispatcher

  "An actor snake" should {
    "flatten" in {
      val expected = Seq(Random.nextInt(), Random.nextInt(), Random.nextInt(), Random.nextInt())
      val snake = new SeqSnake(expected)
      val receiveDuration = timeout.duration
      val snakeActor = TestActorRef(new SnakeActor[Int](receiveDuration, snake))
      val pageSize = 1 + Random.nextInt(4)
      val target = new ActorSnake[Int](snakeActor, pageSize)
      val actual = Await.result(target.continuation.flatten, timeout.duration)
      actual should equal(expected)
    }

    "error" in {
      val seq = Seq(Random.nextInt(), Random.nextInt(), Random.nextInt(), Random.nextInt())
      val snake = new SeqSnake(seq, FutureError(new NoSuchElementException("test")))
      val receiveDuration = timeout.duration
      val snakeActor = TestActorRef(new SnakeActor[Int](receiveDuration, snake))
      val pageSize = 2
      val target = new ActorSnake[Int](snakeActor, pageSize)

      intercept[NoSuchElementException] {
        Await.result(target.continuation.flatten, timeout.duration)
      }
    }

    "size" in {
      val expected = Seq(Random.nextInt(), Random.nextInt(), Random.nextInt(), Random.nextInt())
      val snake = new SeqSnake(expected)
      val receiveDuration = timeout.duration
      val snakeActor = TestActorRef(new SnakeActor[Int](receiveDuration, snake))
      val pageSize = 1 + Random.nextInt(4)
      val target = new ActorSnake[Int](snakeActor, pageSize)
      val actual = Await.result(target.size, timeout.duration)
      actual should equal(expected.size)
    }
  }
}

