package com.peterpotts.snake

import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout
import com.peterpotts.snake.SnakeActor._

import scala.concurrent.{ExecutionContext, Future}

class ActorSnake[T](
  snakeActor: ActorRef,
  pageSize: Int,
  query: Query[T] = Query.empty[T])(implicit executionContext: ExecutionContext, timeout: Timeout)
  extends Snake[T] {
  def :+(query: Query[T]) = new ActorSnake(snakeActor, pageSize, this.query :+ query)

  def continuation: Continuation[T] = Continuation(first())

  def size: Future[Int] = (snakeActor ? Size(query)).mapTo[Int]

  private def first(): Called[T] = snakeActor ? Iterate(query, pageSize) map {
    case Done(seq: Seq[T]) => cons(seq, FutureNone)
    case Cursor(seq: Seq[T], cursorActor) => cons(seq, next(cursorActor))
  }

  private def next(cursorActor: ActorRef): Called[T] = cursorActor ? Next map {
    case Done(seq: Seq[T]) => cons(seq, FutureNone)
    case Continue(seq: Seq[T]) => cons(seq, next(cursorActor))
  }

  private def cons(seq: Seq[T], call: Called[T]) = seq.headOption.map(_ -> Continuation.sequence(seq.tail, call))
}
