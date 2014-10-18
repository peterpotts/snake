package com.peterpotts.snake.predicate

case object True extends Predicate[Any] {
  def apply(value: Any) = true

  override def toString() = "true"
}
