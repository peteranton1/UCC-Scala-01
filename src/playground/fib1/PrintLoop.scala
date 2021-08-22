package playground.fib1

trait PrintLoop {

  def StopWatch(): Long = System.currentTimeMillis()

  def printLoop(n: Int,
                 fn:Int => BigInt): Unit = {
    val start = StopWatch()
    print("(")
    for (a <- 0 to n) {
      if(a % 5 == 0) println("|")
      print(fn(a))
      if(a != n) print(" ")
    }
    val diff = StopWatch() - start
    println(s") $diff millis")
  }

}
