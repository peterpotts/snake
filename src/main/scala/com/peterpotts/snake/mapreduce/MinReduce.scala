package com.peterpotts.snake.mapreduce

import com.peterpotts.snake.coercion.Min

case object MinReduce extends ReduceFunction[Any, Any] {
  def apply(key: Any, values: Traversable[Any]): Any = values.reduce(Min.apply)

  override def toString() = "Min"
}
