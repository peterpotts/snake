package com.peterpotts.snake

import akka.actor._
import com.peterpotts.snake.SnakeActor._

import scala.collection.immutable.Queue
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

class CursorActor[T](receiveTimeout: Duration, pageSize: Int, continuation: Continuation[T])
  extends Actor with ActorLogging {
  override def preStart() {
    context.setReceiveTimeout(receiveTimeout)
  }

  def receive = {
    case ReceiveTimeout => self ! PoisonPill
    case First =>
      log.debug("RECEIVED <<<< First")
      context.become(busy(Queue.empty))
      iterate(cursor = true, sender(), pageSize, Queue.empty[T], continuation)
  }

  private def busy(queue: Queue[ActorRef]): Receive = {
    case ReceiveTimeout => self ! PoisonPill
    case Next =>
      log.debug("RECEIVED <<<< Next")
      context.become(busy(queue.enqueue(sender())))
    case hasNext: HasNext[T] =>
      log.debug("RECEIVED <<<< HasNext")
      if (queue.isEmpty) {
        context.become(idle(hasNext.continuation))
      } else {
        context.become(busy(queue.tail))
        iterate(cursor = false, queue.head, pageSize, Queue.empty[T], hasNext.continuation)
      }
  }

  private def idle(continuation: Continuation[T]): Receive = {
    case ReceiveTimeout => self ! PoisonPill
    case Next =>
      log.debug("RECEIVED <<<< Next")
      context.become(busy(Queue.empty))
      iterate(cursor = false, sender(), pageSize, Queue.empty[T], continuation)
  }

  private def iterate(
    cursor: Boolean,
    requester: ActorRef,
    countdown: Int,
    seq: Seq[T],
    continuation: Continuation[T]) {
    if (countdown == 0) {
      log.debug("SENDING >>>> {}", if (cursor) "Cursor" else "Continue")
      requester ! (if (cursor) Cursor(seq, self) else Continue(seq))
      self ! HasNext(continuation)
    } else {
      continuation.called.onComplete {
        case Success(Some((head, tail))) =>
          iterate(cursor, requester, countdown - 1, seq :+ head, tail)
        case Success(None) =>
          log.debug("SENDING >>>> Done")
          requester ! Done(seq)
          self ! PoisonPill
        case Failure(exception) =>
          log.debug("SENDING >>>> Error")
          requester ! Status.Failure(exception)
          self ! PoisonPill
      }(context.dispatcher)
    }
  }
}
