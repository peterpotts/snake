package com.peterpotts.snake.predicate

import com.peterpotts.snake.coercion.Compare

case class EqualTo[T](extractor: Extractor[T], value: Any) extends Predicate[T] {
  def apply(argument: T) = Compare(extractor(argument), value) == 0

  override def toString() = s"$extractor == $value"
}
