package lectures.part3fp

import exercises.{Cons, Empty}

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // print out all combinations between two lists
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")
  // List("a1","a2"... "a4"... "d4")


  val combinations = numbers
    .filter(n => n % 2 == 0)
    .flatMap(n => chars
      .flatMap(c => colors
        .map(color => "" + c + n + " " + color)))
  println(combinations)

  // for combinations
  val forCombinations = for {
    n <- numbers if (n % 2 == 0)
    c <- chars
    color <- colors
  } yield "" + c + n + " " + color
  println(forCombinations)

  // syntax overload
  list.map { x =>
    x * 2
  }

  /*
  1. see if MyList supports for comprehensions
      map(f: A => B) => MyList[B]
      filter(p: A => Boolean) => MyList[A]
      flatMap(d: A => MyList[B]) => MyList[B]
  2. implement a small collection of at most one element
    - Maybe[+T]
    - map, flatMap, filter
   */

  val nums = Cons("1", Cons("2", Cons("3", Empty)))
  val names = Cons("Al", Cons("Be", Cons("Cy", Empty)))
  println(names)

  val forComp1 = for {
    name <- names
    num <- nums
  } yield name + num
  println(forComp1)

}
