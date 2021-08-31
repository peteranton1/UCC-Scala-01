package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // Seq
  val aSequence = Seq(1,3,2,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5,6,7))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)

  // lists
  val aList = List(1,2,3)
  val prepended1 = 42 :: aList
  println(prepended1)
  val prepended2 = 42 +: aList :+ 44
  println(prepended2)

  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("=|="))

  // Arrays
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[String](3)
  println(threeElements.mkString("Array(", ", ", ")"))
  threeElements.foreach(println)
  // syntax sugar for
  // numbers.update(2,0)
  numbers(2) = 0
  println(numbers.mkString(" "))

  // arrays and sequences
  // implicit conversion
  val numbersSeq: Seq[Int] = numbers
  println(numbersSeq) // ArraySeq(1,2,0,4)

  // Vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  val maxRuns = 1000
  val maxCapacity = 1_000_000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      // operation
      collection
        .updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  println("1time   list: " + getWriteTime(numbersList))
  println("1time vector: " + getWriteTime(numbersVector))
  println("2time   list: " + getWriteTime(numbersList))
  println("2time vector: " + getWriteTime(numbersVector))
  println("3time   list: " + getWriteTime(numbersList))
  println("3time vector: " + getWriteTime(numbersVector))
}



