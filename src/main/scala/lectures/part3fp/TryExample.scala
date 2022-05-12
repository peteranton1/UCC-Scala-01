package lectures.part3fp

import scala.util.Try

object TryExample extends App {

  def tryOne(myString: String): Int = {
    try {
      Integer.parseInt(myString)
    }catch {
      case e: Exception => return -1
    }
  }

  def tryTwo(myString: String): Int = {
    Try(Integer.parseInt(myString))
      .getOrElse(-1)
  }

  def tryBoth(): Unit = {
    val tests = List(
      ("1",1),
      ("199",199),
      ("-1",-1),
      ("A",-1),
      ("",-1),
    )
    tests.foreach(pair =>{
      val actual1 = tryOne(pair._1)
      val actual2 = tryTwo(pair._1)
      println(s"tryOne(${pair._1})=$actual1 = ${pair._2==actual1}")
      println(s"tryTwo(${pair._1})=$actual2 = ${pair._2==actual2}")
    })
  }
  tryBoth()
}
