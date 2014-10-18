package com.peterpotts.snake

import com.peterpotts.snake.predicate.{And, Predicate}

case class Query[T](
  predicate: Option[Predicate[T]],
  ordering: Option[Ordering[T]],
  skip: Option[Int],
  limit: Option[Int]) extends Queryable[Query, T] {
  override def filter(predicate: Predicate[T]) = copy(
    predicate = Some(this.predicate.map(left => And(Seq(left, predicate))).getOrElse(predicate)))

  override def sorted(implicit ordering: Ordering[T]) = copy(ordering = Some(ordering))

  override def drop(skip: Int): Query[T] = copy(
    skip = Some(this.skip.map(_ + skip).getOrElse(skip)),
    limit = limit.map(_ - skip))

  override def take(limit: Int): Query[T] = copy(limit = Some(this.limit.map(math.min(_, limit)).getOrElse(limit)))

  def :+(that: Query[T]): Query[T] = {
    val filtered = that.predicate.map(filter).getOrElse(this)
    val sorted = that.ordering.map(filtered.sorted(_)).getOrElse(filtered)
    val dropped = that.skip.map(sorted.drop).getOrElse(sorted)
    that.limit.map(dropped.take).getOrElse(dropped)
  }
}

object Query {
  def empty[T] = Query[T](None, None, None, None)
}
