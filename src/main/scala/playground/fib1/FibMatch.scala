package playground.fib1

object FibMatch extends App with PrintLoop {

  def fibMatch(n: Int): BigInt = n match {
    case 0 | 1 => BigInt(n)
    case _ => fibMatch(n - 1) + fibMatch(n - 2)
  }

  printLoop(40, n => fibMatch(n))
}
