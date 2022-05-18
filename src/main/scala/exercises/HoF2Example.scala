package exercises

object HoF2Example extends App {

  /*
  Exercise 2:

  Make a function which accepts a function f,
  a count n. and an input value x and returns
  the function f n times on the value x
   */

  def nTimesBetter(f: Int => Int, n: Int): Int => Int = {
    if(n <= 0) (x: Int) => x // identity
    else (x: Int) => nTimesBetter(f, n-1)(f(x))
  }

  val plusOneF: Int => Int = _ + 1

  val plusFiveF = nTimesBetter(plusOneF, 5)

  val x = 10

  println(plusFiveF(x))

}
