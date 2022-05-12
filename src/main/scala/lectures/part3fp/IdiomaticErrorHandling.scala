package lectures.part3fp

class IdiomaticErrorHandling {

}
object IdiomaticErrorHandling {

  // 1
  val magicChar = try {
    // danger
    val scala: String = "Scala"
    scala.charAt(20)
  } catch {
    case e: NullPointerException => 'z'
    case r: RuntimeException => 's'
  } finally {
    // code to clean up resources
  }
  /*
  Pros: expression
  Cons: Cumbersome
   */

  // 2
  import scala.util.Try

  val aTry = Try(2) // Try.apply(2) = Success(2)
  val aFail: Try[Char] = Try {
    // code block
    val scala: String = "Scala"
    scala.charAt(20)
  } // Failure(StringOOBException)
  /*
  Pros: Care about value not exception
  map, flatMap, filter, for-comprehensions
  APIs for recovering from exceptions
  pattern matching
   */

  val aModifiedTry = aTry.map(_ + 2) // Success(4)
  val aRecoveredFailure = aFail.recover {
    case e: RuntimeException => 's'
  }
  val aChainedComputation = for {
    x <- aTry
    y <- aRecoveredFailure
  } yield (x, y)

  val aRight: Either[String, Int] = Right(22)
  val aLeft: Either[String, Int] = Left("Fail")
  val aModifiedRight = aRight.map(_ + 1) // Right(23)
  // map, flatMap, filter
  // for comprehensions

  /*
  Pros
  - Error can be any type
  - for comprehensions
  APIS for manipulating left and right
   */

  // 4
  // Validated
//  import cats.data.validated
//  val aValidated: Validated[String, Int] =
//    Validated.valid(42)
//  val anInvalidated: Validated[String, Int] =
//    Validated.invalid("Error condition")
//  val aTest: Validated[String, Int] =
//    Validated.cond(42 > 39, 23, "The meaning is wrong")
//
//  def validatePositive(n: Int): Validated[List[String], Int] =
//    Validated.cond(n > 0, n, List("n must be positive"))
//
//  def validateSmall(n: Int): Validated[List[String], Int] =
//    Validated.cond(n < 100, n, List("n must be < 100"))
//
//  def validateEven(n: Int): Validated[List[String], Int] =
//    Validated.cond(n % 2 == 0, n, List("n must be even"))
//
//  // implicit semigroup of lists which concatenate
//  import cats.instances.list._
//  implicit val combineIntMax: Semigroup[Int] =
//    Semigroup.instance[Int](Math.max)
//
//  // 3333 => Invalid(List("error 100","error even"))
//  def validate(n: Int): Validated[List[String], Int] = {
//    validatePositive(n) // Valid(3333)
//      .combine(validateSmall(n)) // Invalid(List("n < 100"))
//      .combine(validateEven(n)) // Invalid(List("n must be even"))
//  }
  /*
  Pros
  - combine errors
  - APIs to convert to Try, Either, Option
  Cons
  - can't do flatMap
   */

  def main(args: Array[String]): Unit = {
    println(aChainedComputation)
//    println(validate(3333))
  }

}
