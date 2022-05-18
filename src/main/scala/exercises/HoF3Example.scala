package exercises

object HoF3Example extends App {

  /*
  Exercise 3:

  Make a function that accepts an int and
  returns a function that accepts another
  int and returns the first and second
  ints added together.

   */

  val superAdder = (x: Int) => (y: Int) => x + y

  val add3 = superAdder(3)

  println(add3(99))
  println(superAdder(3)(99))

}
