package com.peterpotts.snake

import scala.concurrent.Future

class SnakeSkin[A, B](
  snake: Snake[A],
  unmarshaller: A => B,
  transformer: Query[B] => Query[A]) extends Snake[B] {
  def :+(query: Query[B]): Snake[B] = new SnakeSkin(snake :+ transformer(query), unmarshaller, transformer)

  def continuation: Continuation[B] = snake.continuation.map(unmarshaller)

  def size: Future[Int] = snake.size
}
