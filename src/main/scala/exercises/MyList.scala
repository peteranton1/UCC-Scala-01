package exercises

import javax.swing.plaf.multi.MultiTreeUI
import scala.annotation.tailrec

/*
singly linked list
head = first element of the list
tail = remainder of the list
isEmpty = is this list empty
add(Int) => new list with element added
toString => a string representation of the list
//
//  def toStringX: String = {
//    @tailrec
//    def inner(t: MyList[A], acc: String):String = t match {
//      case Empty => acc
//      case _ => inner(t.tail, acc + "," + t.head)
//    }
//    "List(" + inner(tail, head.toString) + ")"
//  }
 */
/*
1. Generic trait MyPredicate[-T] with method test[T] => Boolean
2. Generic trait MyTransformer[-A, B] with method transform[A] => B
3. MyList:
- map(transformer) => MyList
- filter(predicate) => MyList
- flatMap(transformer from A to MyList[B]) => MyList[B]
  class EvenPredicate extends MyPredicate[Int]
  class StringToIntTransformer extends MyTransformer[String, Int]

  [1,2,3].map(n * 2) = [2,4,6]
  [1,2,3,4].filter(n % 2 == 0) => [2,4]
  [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4
 */
trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

class EvenPredicate extends MyPredicate[Int] {
  override def test(elem: Int): Boolean = elem % 2 == 0
}

trait MyTransformer[-A, +B] {
  def transform(elem: A): B
}

class TriplerTransformer extends MyTransformer[Int, Int] {
  override def transform(elem: Int): Int = elem * 3
}

class StringToIntTransformer extends MyTransformer[String, Int] {
  override def transform(elem: String): Int = elem.toInt
}

abstract class MyList[+A] {

  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def printElements: String

  override
  def toString: String = "[" + printElements + "]"

  def map[B >: A](t: MyTransformer[A, B]): MyList[B]

  def flatMap[B >: A](t: MyTransformer[A, MyList[B]]): MyList[B]

  def filter[B >: A](p: MyPredicate[A]): MyList[B]

  def ++[B >: A](list: MyList[B]): MyList[B]

  def foreach[B >: A](t: MyTransformer[A, B]): Any
}

object Empty extends MyList[Nothing] {

  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyList[B] = Cons(element, Empty)

  def printElements: String = ""

  def map[B >: Nothing](t: MyTransformer[Nothing, B]): MyList[B] =
    Empty

  def flatMap[B >: Nothing](t: MyTransformer[Nothing, MyList[B]]): MyList[B] =
    Empty

  def filter[B >: Nothing](p: MyPredicate[Nothing]): MyList[B] =
    Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  def foreach[B >: Nothing](t: MyTransformer[Nothing, B]): Any = ()
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {

  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyList[B] = Cons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  def filter[B >: A](p: MyPredicate[A]): MyList[B] =
    if (p.test(head)) Cons(head, tail.filter(p))
    else tail.filter(p)

  def map[B >: A](trans: MyTransformer[A, B]): MyList[B] =
    Cons(trans.transform(head), tail.map(trans))

  def ++[B >: A](list: MyList[B]): MyList[B] =
    Cons(h, t ++ list)

  def flatMap[B >: A](tran: MyTransformer[A, MyList[B]]): MyList[B] = {
    tran.transform(head) ++ tail.flatMap(tran)
  }

  def foreach[B >: A](tran: MyTransformer[A, B]): Any = {
    tran.transform(this.head)
    tail.foreach(tran)
    ()
  }
}

object RunMyList extends App {
  val list0 = Empty add 4
  val list1 = Cons(1, Cons(2, Cons(3, Empty))) add 4
  val list2 = Cons("A", Cons("B", Cons("C", Empty))) add "D"
  println(s"$list0, $list1, $list2")

  val listOfIntegers: MyList[Int] = Empty
  val listOfStrings: MyList[String] = Empty

  //  val list2a = Cons("1",Cons("2",Cons("3", Empty)))
  //  val list2b = list2a.map(new StringToIntTransformer)
  //  println(s"map($list2a) = $list2b")

  val list1a = Cons(1, Cons(2, Cons(3, Empty)))
  val list1b = list1a.map(new TriplerTransformer)
  println(s"map($list1a) = $list1b")

  val list1c = list1a.filter(new EvenPredicate)
  println(s"filter($list1a) = $list1c")
  /*
  1. Generic trait MyPredicate[-T] with method test[T] => Boolean
  2. Generic trait MyTransformer[-A, B] with method transform[A] => B
  3. MyList:
  - map(transformer) => MyList
  - filter(predicate) => MyList
  - flatMap(transformer from A to MyList[B]) => MyList[B]
    class EvenPredicate extends MyPredicate[Int]
    class StringToIntTransformer extends MyTransformer[String, Int]

    [1,2,3].map(n * 2) = [2,4,6]
    [1,2,3,4].filter(n % 2 == 0) => [2,4]
    [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4
   */
  val list1d = list1a.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(elem: Int): MyList[Int] =
      Cons(elem, Cons(elem + 1, Empty))
  })
  println(s"flatMap($list1a) = $list1d")

//  val xx = for {
//    n <- list1a
//    c <- list2
//  } yield "" + n + c
//  println(xx)
}