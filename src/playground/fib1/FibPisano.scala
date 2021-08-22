package playground.fib1

import scala.annotation.tailrec

object FibPisano extends App with PrintLoop {

  def fibPisano( n : Int) : Int = {
    @tailrec
    def fib_tail(n: Int,
                 first: Int,
                 second: Int): Int = n match {
      case 0 => first
      case _ => fib_tail(n - 1, second,
        (first + second) % 1000000)
    }
    fib_tail( n % 1500000, 0, 1)
  }

  printLoop(10000,n => fibPisano(n))
}
