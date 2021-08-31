package lectures.part1basics

object StringOps extends App {

  val str: String =
    "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ","-"))
  println(str.toLowerCase())
  println(str.length)

  val aNumberString = "2"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z')
  println(str.reverse)
  println(str.take(2))

  // scala specific String interpolators
  // s-interpolators
  val name = "David"
  val age = 12
  val greeting = s"Hello my name is $name " +
    s"and I am $age years old"
  println(greeting)
  val greeting2 = s"Hello my name is $name " +
    s"and I will be turning ${age + 1} years old"
  println(greeting2)

  // f-interpolators
  val speed = 0.2
  val num = 1
  val myth = f"$name%s can eat $speed%2.2f burgers " +
    f"per minute ($num%03d)"
  println(myth)

  // raw-interpolators
  println(raw"This is a \n newline")
  println("This is a \n newline")

}
