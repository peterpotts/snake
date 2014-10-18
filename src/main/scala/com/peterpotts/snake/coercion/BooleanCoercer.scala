package com.peterpotts.snake.coercion

/**
 * Converts any recognized representation of a boolean to a Boolean value and throws an exception otherwise.
 */
case object BooleanCoercer extends Coercer[Boolean] {
  def apply(any: Any): Boolean = any match {
    case value: Boolean => value
    case value: Short => value != 0
    case value: Int => value != 0
    case value: Long => value != 0
    case value: Float => value != 0
    case value: Double => value != 0
    case value => value.toString.toBoolean
  }
}
