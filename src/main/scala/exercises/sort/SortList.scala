package exercises.sort

import scala.annotation.tailrec

object SortList extends App {

  def insertSort(list: List[Int]): List[Int] = {
    def insert(number:Int, sortedList: List[Int]): List[Int] =
      if(sortedList.isEmpty || number < sortedList.head)
        number :: sortedList
      else
        sortedList.head :: insert(number, sortedList.tail)

    if(list.isEmpty)
      list
    else
      insert(list.head, insertSort(list.tail))
  }

  def insertBetter(list: List[Int]): List[Int] = {
    @tailrec
    def insertTailRec(number:Int,
                      sortedList: List[Int],
                      acc: List[Int]): List[Int] =
      if(sortedList.isEmpty || number <= sortedList.head)
        acc.reverse ++ (number :: sortedList)
      else
        insertTailRec(number, sortedList.tail, sortedList.head :: acc)

    @tailrec
    def sortTailRec(list: List[Int], acc: List[Int]): List[Int] =
      if(list.isEmpty) acc
      else
        sortTailRec(list.tail, insertTailRec(list.head, acc, Nil))
    sortTailRec(list, Nil)
  }

  val input1: List[Int] = List(4,2,3,1)
  val input2: List[Int] = (1 to 100000).reverse.toList

  println(s"insertSort($input1)=${insertSort(input1)}")
  println(s"insertBetter($input1)=${insertBetter(input1)}")
  //println(s"insertBetter(100000)=${insertBetter(input2)}")
}
