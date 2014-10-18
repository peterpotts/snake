package com.peterpotts.snake

import java.util.concurrent.{CountDownLatch, TimeUnit}

import org.junit.runner.RunWith
import org.mockito.Mockito._
import org.scalatest.junit.JUnitRunner
import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, WordSpec}

import scala.collection.mutable
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Random, Success}

@RunWith(classOf[JUnitRunner])
class ContinuationTest extends WordSpec with Matchers with MockitoSugar {
  "A continuation" should {
    "be lazy" in {
      val fetch = mock[() => String]
      val value = Random.nextString(4)
      when(fetch.apply()) thenReturn value

      val target = Continuation[String](FutureSome(fetch() ->
        Continuation[String](FutureSome(fetch() ->
          Continuation[String](FutureNone)))))

      val pair = Await.result(target.called, 1.second)
      pair.get._1 should equal(value)
      verify(fetch, times(1)).apply()
    }

    "map" in {
      val seq = Seq(Random.nextInt(), Random.nextInt(), Random.nextInt(), Random.nextInt())
      val continuation = Continuation.sequence[Int](seq)
      val actual = continuation.map(_ * 2).flatten
      val expected = continuation.flatten.map(_.map(_ * 2))
      Await.result(actual, 1.second) should equal(Await.result(expected, 1.second))
    }

    "flatten" in {
      val expected = Seq(Random.nextString(4), Random.nextString(4), Random.nextString(4), Random.nextString(4))
      val actual = Continuation.sequence(expected).flatten
      Await.result(actual, 1.second) should equal(expected)
    }

    "on complete" in {
      val expected = Seq(Random.nextString(4), Random.nextString(4), Random.nextString(4), Random.nextString(4))
      val latch = new CountDownLatch(expected.size)
      var actual = mutable.ListBuffer[String]()

      Continuation.sequence(expected).onComplete {
        case Success(string) =>
          actual += string
          latch.countDown()
        case _ => fail()
      }

      if (latch.await(1L, TimeUnit.SECONDS))
        actual should equal(expected)
      else
        fail()
    }

    "grouped" in {
      val seq = Seq(Random.nextString(4), Random.nextString(4), Random.nextString(4), Random.nextString(4))
      val pageSize = 1 + Random.nextInt(4)
      val actual = Continuation.sequence(seq).grouped(pageSize).flatten
      val expected = seq.grouped(pageSize).toSeq
      Await.result(actual, 1.second) should equal(expected)
    }
  }
}

