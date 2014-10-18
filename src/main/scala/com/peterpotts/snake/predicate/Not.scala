package com.peterpotts.snake.predicate

case class Not[T](predicate: Predicate[T]) extends Predicate[T] {
  def apply(value: T) = !predicate(value)

  override def toString() = s"!$predicate"
}
