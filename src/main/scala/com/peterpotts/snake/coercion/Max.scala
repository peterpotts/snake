package com.peterpotts.snake.coercion

object Max extends Coercion[Any] {
  def boolean(left: Boolean, right: Boolean): Any = if (left > right) left else right

  def int(left: Int, right: Int): Any = if (left > right) left else right

  def long(left: Long, right: Long): Any = if (left > right) left else right

  def double(left: Double, right: Double): Any = if (left > right) left else right

  def string(left: String, right: String): Any = if (left > right) left else right
}
