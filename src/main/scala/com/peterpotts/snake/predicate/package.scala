package com.peterpotts.snake

package object predicate {
  type Predicate[-T] = T => Boolean
  type Extractor[-T] = T => Any
}
