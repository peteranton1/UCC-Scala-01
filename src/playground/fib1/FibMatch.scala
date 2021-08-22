package playground.fib1

object FibMatch extends App with PrintLoop {

  def fibMatch(n: Long): Long = n match {
    case 0 | 1 => n
    case _ => fibMatch(n - 1) + fibMatch(n - 2)
  }

  printLoop(10, n => fibMatch(n))
}
