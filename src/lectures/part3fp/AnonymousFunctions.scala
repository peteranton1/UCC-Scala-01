package lectures.part3fp

object AnonymousFunctions extends App {

  // anonymous function or lambda
  val doubler: Int => Int = x => x * 2

  val adder1 = (a: Int, b: Int) => a + b
  val adder2:(Int, Int)=> Int = (a, b) => a + b

  /*
  1. MyList -=replace all function X calls with Lambdas
  2. Rewrite the special adder as an anonymous function.
   */
  val superAdd = (x: Int) => (y:Int) => x + y
  println(superAdd(3)(4))
}
