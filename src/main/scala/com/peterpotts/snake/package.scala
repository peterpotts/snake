package com.peterpotts

import scala.concurrent.Future

package object snake {
  type Called[A] = Future[Option[(A, Continuation[A])]]

  def FutureSome[A](pair: (A, Continuation[A])): Called[A] = Future.successful(Some(pair))

  val FutureNone = Future.successful[Option[Nothing]](None)

  val FutureNil = Future.successful[List[Nothing]](Nil)

  def FutureError(exception: Throwable) = Future.failed[Option[Nothing]](exception)

}
