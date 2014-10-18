package com.peterpotts.snake

import com.peterpotts.snake.predicate.{And, Predicate}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class QueryTest extends WordSpec with Matchers {
  "A query" should {
    "store a predicate for filter" in {
      val filter: Predicate[Int] = _ > 0
      val actual = Query.empty[Int].filter(filter)
      val expected = Query[Int](Some(filter), None, None, None)
      actual should equal(expected)
    }

    "store an ordering for sorted" in {
      implicit val ordering: Ordering[Int] = new Ordering[Int] {
        def compare(x: Int, y: Int): Int = x - y
      }

      val actual = Query.empty[Int].sorted
      val expected = Query[Int](None, Some(ordering), None, None)
      actual should equal(expected)
    }

    "store a number for drop" in {
      val skip = Random.nextInt()
      val actual = Query.empty[Int].drop(skip)
      val expected = Query[Int](None, None, Some(skip), None)
      actual should equal(expected)
    }

    "filter a filter consistently" in {
      val positive: Predicate[Int] = _ > 0
      val negative: Predicate[Int] = _ < 0
      val actual = Query.empty[Int].filter(positive).filter(negative)
      val expected = Query[Int](Some(And[Int](Seq(positive, negative))), None, None, None)
      actual should equal(expected)
    }

    "drop then take then drop the take consistently" in {
      val w = Random.nextInt()
      val x = Random.nextInt()
      val y = Random.nextInt()
      val z = Random.nextInt()
      val actual = Query.empty[Int].drop(w).take(x).drop(y).take(z)
      val expected = Query[Int](None, None, Some(w + y), Some(math.min(x - y, z)))
      actual should equal(expected)
    }
  }
}


