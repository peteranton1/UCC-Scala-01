package playground.fib1

import scala.annotation.tailrec
import scala.math.BigInt

object FibTailRec extends App with PrintLoop {

  def fibTailRec(n: Int): BigInt = {
    @tailrec
    def fib_tail(n: Int,
                 first: BigInt,
                 second: BigInt): BigInt = n match {
      case 0 => first
      case _ => fib_tail(n - 1, second, first + second)
    }
    fib_tail(n, 0, 1)
  }

  printLoop(10000, n => fibTailRec(n))
}
