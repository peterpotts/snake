package com.peterpotts.snake.coercion

object Compare extends Coercion[Int] {
  def boolean(left: Boolean, right: Boolean): Int = left compare right

  def int(left: Int, right: Int): Int = left compare right

  def long(left: Long, right: Long): Int = left compare right

  def double(left: Double, right: Double): Int = left compare right

  def string(left: String, right: String): Int = left compare right
}
