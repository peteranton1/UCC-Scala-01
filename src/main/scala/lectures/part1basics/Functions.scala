package lectures.part1basics

import scala.annotation.tailrec

object Functions extends App {

  def aFunction(a: String, b: Int): String =
    a + " " + b

  println(aFunction("Hello", 3))

  def aParameterLessFunction(): Int = 42

  println(aParameterLessFunction())
  // can also be used as
  // println(aParameterLessFunction)

  def aRepeatedFunction(a: String, n: Int): String =
    if (n == 1) a
    else a + aRepeatedFunction(a, n - 1)

  println(aRepeatedFunction("Hello", 3))

  def aFunctionWithSideEffects(aString: String): Unit =
    println(aString)

  aFunctionWithSideEffects("Bananas")

  /*
  1. A greeting function (name, age)
  hi my name is david and I am 12 years old
   */
  def aGreetingFunction(name: String, age: Int): String =
    s"Hi, my name is $name and I am $age years old"

  println(aGreetingFunction("David", 12))

  /*
  2. Write factorial function 1 * 2 * 3 * 4
   */
  def factorial(n: Int): Int = {
    @tailrec
    def inner(n0: Int, acc: Int): Int = {
      if (n0 <= 1) acc
      else inner(n0 - 1, acc * n0)
    }

    inner(n, 1)
  }

  println(factorial(5))

  /*
  3. A fibonacci function 0 1 1 2 3 5 8
  4. a function which tests if a number is prime
   */
  def fibonacci(n: Int): Int = {
    //    def inner(a: Int, b: Int): Int = {
    //      if(a <= 0) 0
    //      else if(a == 1) 1
    //      else inner(a - 1)
    // }
    0
  }
}
