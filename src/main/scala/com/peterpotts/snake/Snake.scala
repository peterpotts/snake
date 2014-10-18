package com.peterpotts.snake

import scala.concurrent.Future

trait Snake[T] extends Queryable[Snake, T] {
  def continuation: Continuation[T]

  def size: Future[Int]
}

