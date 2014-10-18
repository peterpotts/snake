package com.peterpotts.snake.coercion

import scala.util.Try

/**
 * Converts any recognised representation of a number to the nearest double precision floating point number and returns
 * NaN otherwise.
 */
case object DoubleCoercer extends Coercer[Double] {
  def apply(any: Any): Double = any match {
    case value: Boolean => if (value) 1.0 else 0.0
    case value: Short => value.toDouble
    case value: Int => value.toDouble
    case value: Long => value.toDouble
    case value: Float => value.toDouble
    case value: Double => value
    case value => Try(value.toString.toDouble).getOrElse(Double.NaN)
  }
}
