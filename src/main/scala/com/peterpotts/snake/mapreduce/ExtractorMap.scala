package com.peterpotts.snake.mapreduce

import com.peterpotts.snake.predicate.Extractor

case class ExtractorMap[T](keyExtractor: Map[String, Extractor[T]], valueExtractor: Extractor[T])
  extends MapFunction[T, Map[String, Any], Any] {
  def apply(argument: T) = Traversable(keyExtractor.mapValues(_(argument)) -> valueExtractor(argument))

  override def toString() = s"$keyExtractor -> $valueExtractor"
}
