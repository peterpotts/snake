package com.peterpotts.snake.coercion

object Add extends Coercion[Any] {
  def boolean(left: Boolean, right: Boolean): Any = int(left, right)

  def int(left: Int, right: Int): Any = left + right

  def long(left: Long, right: Long): Any = left + right

  def double(left: Double, right: Double): Any = left + right

  def string(left: String, right: String): Any = left + right
}
