package com.peterpotts.snake.predicate

case class And[T](predicates: Seq[Predicate[T]]) extends Predicate[T] {
  def apply(value: T) = predicates.map(_(value)).reduce(_ && _)

  override def toString() = predicates.mkString(" && ")
}
