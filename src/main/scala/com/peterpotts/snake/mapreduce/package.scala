package com.peterpotts.snake

package object mapreduce {
  type Emitter[-T, K, V] = T => (K, V)
  type MapFunction[-T, K, V] = T => Traversable[(K, V)]
  type ReduceFunction[K, V] = (K, Traversable[V]) => V
}