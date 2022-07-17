package exercises.monads

/**
 * Taken from:
 * https://www.youtube.com/watch?v=C2w45qRc3aU
 */

object IntroMonads1 extends App {

  case class NumberWithLogs(result: Int,
                            logs: List[String]){

  }

  def square(x: Int): NumberWithLogs = {
    val result: Int = x * x
    NumberWithLogs(
      result,
      List(s"Squared ${x} to get ${result}")
    )
  }

  def addOne(x: Int): NumberWithLogs = {
    val result: Int = x + 1
    NumberWithLogs(
      result,
      List(s"Added 1 to ${x} to get ${result}")
    )
  }

  def wrapWithLogs(x: Int): NumberWithLogs = {
    val result: Int = x
    NumberWithLogs(
      result,
      List()
    )
  }

  def runWithLogs(
                 input: NumberWithLogs,
                 transform: Int => NumberWithLogs
                 ): NumberWithLogs = {
    val newNumberWithLogs = transform(input.result)
    new NumberWithLogs(
      newNumberWithLogs.result,
      input.logs ++ newNumberWithLogs.logs
    )
  }

  val a = wrapWithLogs(5)
  val b = runWithLogs(a, addOne)
  val c = runWithLogs(b, square)

  println(s" Result:")
  println(s" result: ${c.result}, logs: ${c.logs}")
}
