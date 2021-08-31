package lectures.part1basics

import playground.fib1.FibTailRec.printLoop

import scala.annotation.tailrec
import scala.math.BigInt

object Recursion extends App {

  def factorial1(n: Int): Int =
    if(n <= 1) 1
    else n * factorial1(n-1)

  def factorial2(n: Int): Int =
    if(n <= 1) 1
    else {
      println(s"before call n = $n")
      val result = n * factorial2(n - 1)
      println(s"after call result = $result")
      result
    }
  // println(factorial2(10))

  def anotherFactorial(n: Int):Int = {
    def factorialHelper(x: Int, acc: Int): Int = {
      if(x <= 1) acc
      else factorialHelper(x - 1, x * acc)
    }
    println(s"fact Helper n = $n")
    factorialHelper(n, 1)
  }
  // println(anotherFactorial(10))

  /*
  1. Concatenate a String n times
   */
  def mkString(n: Int, s: String): String = {
    @tailrec
    def inner(x: Int, s1: String, acc: String): String = {
      if(x <= 0) acc
      else {
        inner(x - 1, s1, s1 + acc)
      }
    }
    inner(n, s, "")
  }
  println(mkString(3,"Hello"))

  /*
  2. IsPrime tail recursive
   */
  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailrec(t: Int, isStillPrime: Boolean) : Boolean = {
      def isNotFactor = {
        n % t != 0
      }
      if(!isStillPrime) false
      else if(t <= 1) true
      else isPrimeTailrec(t - 1, isNotFactor && isStillPrime)
    }
    isPrimeTailrec(n / 2, true)
  }
  println("isPrime(): " + (1 to 20).filter(isPrime))
  println("isPrime(2003): " + isPrime(2003))
  println("isPrime(629): " + isPrime(629))

  /*
  3. Fibonacci tail recursive
   */
  def fibTailRec(n: Int): BigInt = {
    @tailrec
    def fib_tail(i: Int,
                 last: BigInt,
                 nextToLast: BigInt
                ): BigInt = i match {
      case 0 => nextToLast
      case _ => fib_tail(i - 1, last + nextToLast, last)
    }
    fib_tail(n, 1, 0)
  }

  def fibonacci(n: Int): BigInt = {
    @tailrec
    def fiboTailrec(i: Int,
                    last: BigInt,
                    nextToLast: BigInt
                   ): BigInt =
      if(i >= n) last
      else fiboTailrec(i + 1, last + nextToLast, last)

    if(n <= 2) 1
    else fiboTailrec(2, 1, 1)
  }

  println("fibTailRec(): " + (1 to 10).map(n => fibTailRec(n)))
  println("fibonacci(): " + (1 to 10).map(n => fibonacci(n)))
}
