package com.peterpotts.snake

import akka.actor.{Actor, ActorRef, Props}
import akka.pattern.pipe
import com.peterpotts.snake.SnakeActor.{First, Iterate, Size}

import scala.concurrent.duration.Duration

class SnakeActor[A](receiveTimeout: Duration, snake: Snake[A]) extends Actor {
  implicit val executionContext = context.dispatcher

  def receive = {
    case size: Size[A] => (snake :+ size.query).size pipeTo sender
    case iterate: Iterate[A] =>
      def creator = new CursorActor(receiveTimeout, iterate.pageSize, (snake :+ iterate.query).continuation)
      context.actorOf(Props[CursorActor[A]](creator)) forward First
  }
}

object SnakeActor {

  case class Iterate[A](query: Query[A], pageSize: Int)

  case object First

  case object Next

  case class HasNext[A](continuation: Continuation[A])

  case class Cursor[A](seq: Seq[A], cursorActor: ActorRef)

  case class Done[A](seq: Seq[A])

  case class Continue[A](seq: Seq[A])

  case class Size[A](query: Query[A])

}
