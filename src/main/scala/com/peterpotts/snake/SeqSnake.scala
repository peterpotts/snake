package com.peterpotts.snake

import scala.concurrent.{ExecutionContext, Future, Promise}

class SeqSnake[A](
  seq: Seq[A],
  call: => Called[A] = FutureNone,
  query: Query[A] = Query.empty[A])(implicit executionContext: ExecutionContext) extends Snake[A] {
  def :+(query: Query[A]): Snake[A] = new SeqSnake(seq, call, this.query :+ query)

  def continuation: Continuation[A] = Continuation.sequence(result, call)

  def size: Future[Int] = Promise.successful(result.size).future

  private lazy val result: Seq[A] = {
    val filtered = query.predicate.map(seq.filter).getOrElse(seq)
    val sorted = query.ordering.map(filtered.sorted(_)).getOrElse(filtered)
    val dropped = query.skip.map(sorted.drop).getOrElse(sorted)
    query.limit.map(dropped.take).getOrElse(dropped)
  }

  override def toString = seq.mkString("SeqSnake(", ", ", ")")
}
