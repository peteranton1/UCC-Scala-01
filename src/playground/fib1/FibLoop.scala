package playground.fib1

object FibLoop extends App with PrintLoop {

  def fibLoop(n: Int): BigInt = {
    var first = BigInt(0)
    var second = BigInt(1)
    var count = n

    while(count > 0){
      val sum = first + second
      first = second
      second = sum
      count = count - 1
    }
    first
  }

  printLoop(100,n => fibLoop(n))
}
