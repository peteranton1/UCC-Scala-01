package exercises

import scala.annotation.tailrec

object HoF1Example extends App {

  /*
  Exercise 1:

  Make a function which accepts a function f,
  a count n. and an input value x and performs
  the function f n times on the value x
   */

  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if(n <= 0) x // identity
    else nTimes(f, n-1, f(x))
  }

  val plusOneF: Int => Int = _ + 1

  val x = 10

  println(nTimes(plusOneF, 5, x))

}
