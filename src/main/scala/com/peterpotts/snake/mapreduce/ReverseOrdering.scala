package com.peterpotts.snake.mapreduce

case class ReverseOrdering[T](ordering: Ordering[T]) extends Ordering[T] {
  def compare(left: T, right: T): Int = -ordering.compare(left, right)

  override def toString = s"-$ordering"
}
