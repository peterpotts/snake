package com.peterpotts.snake.mapreduce

import com.peterpotts.snake.coercion.Add

case object SumReduce extends ReduceFunction[Any, Any] {
  def apply(key: Any, values: Traversable[Any]): Any = values.reduce(Add.apply)

  override def toString() = "Sum"
}
