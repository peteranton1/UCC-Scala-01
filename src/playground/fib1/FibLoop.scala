package playground.fib1

object FibLoop extends App with PrintLoop {

  def fibLoop(n: Long): Long = {
    var first = 0
    var second = 1
    var count = n

    while(count > 0){
      val sum = first + second
      first = second
      second = sum
      count = count - 1
    }
    first
  }

  printLoop(10,n => fibLoop(n))
}
