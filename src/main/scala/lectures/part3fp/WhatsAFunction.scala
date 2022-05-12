package lectures.part3fp

object WhatsAFunction extends App {

  // DREAM: use functions as First Class elements.
  // problem: oop

  val double = new MyFunction[Int, Int] {
    override
    def apply(element: Int): Int = element * 2

  }

  println(double(2))

  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder1 = new Function2[Int,Int,Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  val adder2 = new ((Int, Int) => Int) {
    override def apply(a: Int, b: Int): Int = a + b
  }

  /**
   * 1. define function which takes two strings and concatenate.
   * 2. transform the MyPredicate and MyTransformer into
   * Function types.
   * 3. Define a function which takes an int and returns another
   * function which takes an int and returns an int.
   * - Whats the type of this function.
   * - how to do it.
   */
  def concatenateFun1(s1: String, s2: String): String = s1 + s2
  val concatenateFun2 = (a:String, b:String) => a + b
  println(concatenateFun1("a","b"))
  println(concatenateFun2("a","b"))

  def aFunction(a: Int): Int => Int =
    (b => a*b)
  println(aFunction(42)(2))

  val superAdder: Function[Int, Function1[Int, Int]] =
    new Function1[Int, Function1[Int, Int]] {
      override def apply(x: Int): Function1[Int, Int] =
        new Function1[Int, Int]{
        override def apply(y: Int): Int = x + y
      }
    }
  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // Curried function


}

trait MyFunction[A, B] {
  def apply(element: A): B
}