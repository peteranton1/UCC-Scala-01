package main.scala.playground.palin

import scala.annotation.tailrec

class Palindrome {
  def isPalindrome(str: String): Boolean = {
    @tailrec
    def inner(str: String, left: Int, right: Int): Boolean = {
      if (left >= right) true
      else if (str.charAt(left) != str.charAt(right)) false
      else inner(str, left + 1, right - 1)
    }

    if (str == null || str.isEmpty) false
    else inner(str, 0, str.length - 1)
  }

}

object Palindrome extends App {
  val values: Array[String] = Array(
    "abba",
    "madam",
    "amanaplanacanalpanama",
    "banana"
  )
  val inst = new Palindrome()
  values.foreach(v => {
    val result = inst.isPalindrome(v)
    println(s"${v} = ${result}")
  })
}
