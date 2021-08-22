package playground.fib1

import scala.annotation.tailrec

object FibTailRec extends App with PrintLoop {

  def fibTailRec(n: Long): Long = {
    @tailrec
    def fib_tail(n: Long,
                 first: Long,
                 second: Long): Long = n match {
      case 0 => first
      case _ => fib_tail(n - 1, second, first + second)
    }
    fib_tail(n, 0, 1)
  }

  printLoop(10, n => fibTailRec(n))
}
