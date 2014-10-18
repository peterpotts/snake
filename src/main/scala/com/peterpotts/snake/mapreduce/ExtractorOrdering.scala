package com.peterpotts.snake.mapreduce

import com.peterpotts.snake.coercion.Compare
import com.peterpotts.snake.predicate.Extractor

case class ExtractorOrdering[T](extractor: Extractor[T]) extends Ordering[T] {
  def compare(left: T, right: T): Int = Compare(extractor(left), extractor(right))

  override def toString = s"$extractor order"
}
