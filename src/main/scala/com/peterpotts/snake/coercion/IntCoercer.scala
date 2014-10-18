package com.peterpotts.snake.coercion

/**
 * Converts any recognized representation of a number to the nearest single precision whole number and throws an
 * exception otherwise.
 */
case object IntCoercer extends Coercer[Int] {
  def apply(any: Any): Int = any match {
    case value: Boolean => if (value) 1 else 0
    case value: Short => value.toInt
    case value: Int => value
    case value: Long => value.toInt
    case value: Float => math.round(value)
    case value: Double => math.round(value).toInt
    case value => value.toString.toInt
  }
}
