package com.peterpotts.snake.mapreduce

import com.peterpotts.snake.predicate._

/**
 * The Star Wars operators.
 */
object DSL {

  implicit class RichExtractor[T](left: Extractor[T]) {
    def |==|(right: Any) = EqualTo(left, right)

    def |!=|(right: Any) = NotEqualTo(left, right)

    def |>|(right: Any) = GreaterThan(left, right)

    def |>=|(right: Any) = GreaterThanOrEqualTo(left, right)

    def |<|(right: Any) = LessThan(left, right)

    def |<=|(right: Any) = LessThanOrEqualTo(left, right)

    def |?|(right: Boolean) = Exists(left, right)

    def |+| = ExtractorOrdering(left)
  }

  implicit class RichPredicate[T](left: Predicate[T]) {
    def |&&|(right: Predicate[T]) = And(Seq(left, right))

    def ||||(right: Predicate[T]) = Or(Seq(left, right))

    def |!| = Not(left)
  }

  implicit class RichOrdering[T](left: Ordering[T]) {
    def |-| = ReverseOrdering(left)
  }

  implicit class RichKeyExtractor[T](left: Map[String, Extractor[T]]) {
    def |->|(right: Extractor[T]) = ExtractorMap(left, right)
  }

}
