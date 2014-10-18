package com.peterpotts.snake

import scala.language.higherKinds

trait Queryable[M[_], T] {
  def filter(predicate: T => Boolean): M[T] = :+(Query(Some(predicate), None, None, None))

  def sorted(implicit ordering: Ordering[T]): M[T] = :+(Query(None, Some(ordering), None, None))

  def drop(skip: Int): M[T] = :+(Query(None, None, Some(skip), None))

  def take(limit: Int): M[T] = :+(Query(None, None, None, Some(limit)))

  def :+(query: Query[T]): M[T]
}
