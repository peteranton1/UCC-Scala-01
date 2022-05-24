package lectures.part3fp

import scala.annotation.tailrec

object InsertSortFP extends App {

  /*
  Given a list of ints, sort in ascending order
  using insertion Sort and functional programming.
   */
  def sortList(list: List[Int]): List[Int] = {
    // insertion sort non-tailrec
    def insert(number: Int, sortedList: List[Int]): List[Int] =
      if(sortedList.isEmpty || number <= sortedList.head)
        number :: sortedList
      else
        sortedList.head :: insert(number, sortedList.tail)

    if(list.isEmpty || list.tail.isEmpty) list
    else insert(list.head, sortList(list.tail))
  }
  val input: List[Int] = List(3,1,4,2)
  val output1 = sortList(input)
  println(s"$input = output1 $output1")

  /*
  sort tail recursively
   */
  def sortBetter(list: List[Int]): List[Int] = {
    @tailrec
    def insertTailRec(number: Int, sortedList: List[Int], acc: List[Int]): List[Int] =
      if(sortedList.isEmpty || number <= sortedList.head)
        acc.reverse ++ (number :: sortedList)
      else
        insertTailRec(number, sortedList.tail, sortedList.head :: acc)

    @tailrec
    def sortTailRec(list: List[Int], acc: List[Int]): List[Int] =
      if (list.isEmpty) acc
      else sortTailRec(list.tail,
        insertTailRec(list.head, acc, Nil))

    sortTailRec(list, Nil)
  }
  val output2 = sortBetter(input)
  println(s"$input = output2 $output2")
}
