package com.peterpotts.snake.coercion

import scala.language.implicitConversions
import scala.util.Try

trait Coercion[T] {
  implicit def booleanToInt(value: Boolean): Int = IntCoercer(value)

  implicit def booleanToLong(value: Boolean): Long = LongCoercer(value)

  implicit def booleanToDouble(value: Boolean): Double = DoubleCoercer(value)

  implicit def booleanToString(value: Boolean): String = StringCoercer(value)

  implicit def intToLong(value: Int): Long = LongCoercer(value)

  implicit def intToDouble(value: Int): Double = DoubleCoercer(value)

  implicit def intToString(value: Int): String = StringCoercer(value)

  implicit def longToDouble(value: Long): Double = DoubleCoercer(value)

  implicit def longToString(value: Long): String = StringCoercer(value)

  implicit def doubleToString(value: Double): String = StringCoercer(value)

  private val lift: Any => Any = {
    case value: Boolean => value
    case value: Int => value
    case value: Long => value
    case value: Double => value
    case value: String =>
      Try(value.toBoolean).getOrElse(
        Try(value.toInt).getOrElse(
          Try(value.toLong).getOrElse(
            Try(value.toDouble).getOrElse(
              Try(value.toBoolean).getOrElse(
                value)))))
    case value: Any => throw new MatchError(value)
  }

  private val lifted: (Any, Any) => T = {
    case (left: Boolean, right: Boolean) => int(left, right)
    case (left: Boolean, right: Int) => int(left, right)
    case (left: Boolean, right: Long) => long(left, right)
    case (left: Boolean, right: Double) => double(left, right)
    case (left: Boolean, right: String) => string(left, right)

    case (left: Int, right: Boolean) => int(left, right)
    case (left: Int, right: Int) => int(left, right)
    case (left: Int, right: Long) => long(left, right)
    case (left: Int, right: Double) => double(left, right)
    case (left: Int, right: String) => string(left, right)

    case (left: Long, right: Boolean) => long(left, right)
    case (left: Long, right: Int) => long(left, right)
    case (left: Long, right: Long) => long(left, right)
    case (left: Long, right: Double) => double(left, right)
    case (left: Long, right: String) => string(left, right)

    case (left: Double, right: Boolean) => double(left, right)
    case (left: Double, right: Int) => double(left, right)
    case (left: Double, right: Long) => double(left, right)
    case (left: Double, right: Double) => double(left, right)
    case (left: Double, right: String) => string(left, right)

    case (left: String, right: Boolean) => string(left, right)
    case (left: String, right: Int) => string(left, right)
    case (left: String, right: Long) => string(left, right)
    case (left: String, right: Double) => string(left, right)
    case (left: String, right: String) => string(left, right)

    case value => throw new MatchError(value)
  }

  def apply(left: Any, right: Any): T = lifted(lift(left), lift(right))

  def boolean(left: Boolean, right: Boolean): T

  def int(left: Int, right: Int): T

  def long(left: Long, right: Long): T

  def double(left: Double, right: Double): T

  def string(left: String, right: String): T
}
