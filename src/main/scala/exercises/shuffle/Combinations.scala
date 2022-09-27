package exercises.shuffle

import scala.annotation.tailrec

class Combinations[T] {
  def getCombinations(handSize: Int, list: List[T]): List[List[T]] = {
    @tailrec
    def select(handSize: Int, list: List[T],
               acc: List[List[T]]): List[List[T]] = {
      val acc1: List[List[T]] = if(acc.isEmpty) list.map(c => List(c)) else acc
      if (handSize == 0) acc1
      else {
        val acc1 = acc.map(accList =>
          list.filterNot(c => accList.contains(c))
        )
        var acc3: List[List[T]] = Nil
        acc.foreach(accList =>
          acc1.foreach(accList2 =>
            if (!accList2.exists(c => accList.contains(c))) {
              val newList3 = accList2 ++ accList
              if(!acc3.exists(accList3 => acc3.contains(newList3))) {
                acc3 = newList3 :: acc3
              }
            }
          )
        )
        select(handSize - 1, list, acc3)
      }
    }
    select(handSize, list, Nil)
  }

}

object Combinations extends App {

  val app = new Combinations[Int]
  val list = List(1, 2, 3, 4)
  val handSize = 2
  val result = app.getCombinations(handSize, list)
  println(s"List Size = ${result.size}")
  result.foreach(println(_))
  println(s"List Size = ${result.size}")
}
