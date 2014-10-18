package com.peterpotts.snake

import scala.collection.immutable.Queue
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

class Continuation[A](call: => Called[A])(implicit executionContext: ExecutionContext) {
  lazy val called = call

  def map[B](transformer: A => B): Continuation[B] = Continuation[B] {
    called.map(_.map {
      case (head, tail) => transformer(head) -> tail.map(transformer)
    })
  }

  def flatten: Future[Seq[A]] = called.flatMap {
    case Some((head, tail)) => tail.flatten.map(head +: _)
    case None => FutureNil
  }

  def onComplete[B](transformer: Try[A] => B) {
    called.onComplete {
      case Success(Some((head, tail))) =>
        transformer(Success(head))
        tail.onComplete(transformer)
      case Success(None) =>
      case Failure(exception) => transformer(Failure(exception))
    }
  }

  def grouped(size: Int): Continuation[Seq[A]] = {
    def iterate(countdown: Int, seq: Seq[A], continuation: Continuation[A]): Called[Seq[A]] = {
      if (countdown == 0)
        FutureSome[Seq[A]](seq -> continuation.grouped(size))
      else
        continuation.called.flatMap {
          case Some((head, tail)) => iterate(countdown - 1, seq :+ head, tail)
          case None if seq.isEmpty => FutureNone
          case None => FutureSome[Seq[A]](seq -> Continuation(FutureNone))
        }
    }

    Continuation(iterate(size, Queue.empty[A], this))
  }
}

object Continuation {
  def apply[A](call: => Called[A])(implicit executionContext: ExecutionContext) = new Continuation(call)

  def sequence[A](seq: Seq[A], call: => Called[A] = FutureNone)(implicit executionContext: ExecutionContext) =
    seq.foldRight(Continuation(call))((head, tail) => Continuation(FutureSome[A](head -> tail)))

  def iterate[A](
    iterator: Iterator[A],
    pageSize: Int,
    call: => Called[A] = FutureNone)(implicit executionContext: ExecutionContext) =
    iterator.zipWithIndex.foldRight(Continuation(call)) {
      case ((head, index), tail) => Continuation {
        if (index % pageSize == 0)
          Future(Some(head -> tail))
        else
          FutureSome[A](head -> tail)
      }
    }
}
