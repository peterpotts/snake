package com.peterpotts.snake.coercion

case object StringCoercer extends Coercer[String] {
  def apply(any: Any): String = any.toString
}
