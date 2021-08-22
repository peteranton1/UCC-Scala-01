package playground.fib1

trait PrintLoop {

  def printLoop(n: Long,
                fn:Long => Long): Unit = {
    print("(")
    for (a <- 0L to n) {
      print(fn(a))
      if(a != n) print(" ")
    }
    println(")")
  }

}
