package exercises

object HoF4Example extends App {

  /*
  Exercise 4:

  Make a function that accepts String and
  a Double in two argument lists and returns
  the Double formatted as a String.
   */
  def curriedFormatter(c: String)(x: Double): String =
    c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.4f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))
}
