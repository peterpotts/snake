package com.peterpotts.snake.predicate

case object False extends Predicate[Any] {
  def apply(value: Any) = false

  override def toString() = "false"
}
