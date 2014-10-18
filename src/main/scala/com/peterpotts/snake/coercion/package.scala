package com.peterpotts.snake

package object coercion {
  type Coercer[+T] = Any => T
}
