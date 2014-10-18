package com.peterpotts.snake.coercion

/**
 * Converts any recognized representation of a number to the nearest double precision whole number and throws an
 * exception otherwise.
 */
case object LongCoercer extends Coercer[Long] {
  def apply(any: Any): Long = any match {
    case value: Boolean => if (value) 1L else 0L
    case value: Short => value.toLong
    case value: Int => value.toLong
    case value: Long => value
    case value: Float => math.round(value).toLong
    case value: Double => math.round(value)
    case value => value.toString.toLong
  }
}
