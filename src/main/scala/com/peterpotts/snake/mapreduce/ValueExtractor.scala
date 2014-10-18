package com.peterpotts.snake.mapreduce

import com.peterpotts.snake.predicate.Extractor

case class ValueExtractor(value: Any) extends Extractor[Any] {
  def apply(argument: Any) = value

  override def toString() = value.toString
}
