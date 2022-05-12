package playground.fib1

object FibStream extends App with PrintLoop {

  /*
  Lazy Evaluation with Scala Streams and
  Memoization. A Stream is similar to a list,
  with the exception that its elements are
  computed lazily, this means that a Stream
  can be infinitely long and only the elements
  requested at a given time will be computed.
  Stream was replaced by LazyList in latest Scala.
   */

  val fib: LazyList[BigInt] = BigInt(0) #::
    BigInt(1) #:: fib.zip(fib.tail)
    .map(p => p._1 + p._2)

  def fibLazyList(n: Int): BigInt = fib(n)

  /*
  Also, we can create a memoize wrapper in
  combination with the previously defined
  stream to keep a cache and make things
  even more performant.
   */

  def memoize[A, B](f: A => B): A => B =
    new collection.mutable.WeakHashMap[A, B] {
      override def apply(a: A) =
        getOrElseUpdate(a, f(a))
    }

  val memoFibLL = memoize(fib)

  printLoop(100, n => fibLazyList(n) )
  printLoop(100, n => memoFibLL(n) )

}
