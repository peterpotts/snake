package com.peterpotts.snake.mapreduce

import com.peterpotts.snake.coercion.Max

case object MaxReduce extends ReduceFunction[Any, Any] {
  def apply(key: Any, values: Traversable[Any]): Any = values.reduce(Max.apply)

  override def toString() = "Max"
}
