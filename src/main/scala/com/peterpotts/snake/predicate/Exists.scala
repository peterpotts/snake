package com.peterpotts.snake.predicate

import scala.util.Try

case class Exists[T](extractor: Extractor[T], sense: Boolean) extends Predicate[T] {
  def apply(argument: T) = Try(extractor(argument)).isSuccess == sense

  override def toString() = if (sense) s"$extractor exists" else s"$extractor not exists"
}
