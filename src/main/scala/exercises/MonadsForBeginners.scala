package exercises

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success

object MonadsForBeginners extends App {

  // Monads are types which can take
  // values and do something with them
  // in a certain structure.

  //
  case class SafeValue[+T](private val internalValue: T) {
    def get: T = synchronized {
      // assume does something interesting
      internalValue
    }

    def flatMap[S](transformer: T => SafeValue[S]): SafeValue[S] =
      synchronized {
        transformer(internalValue)
      }
  }

  // "external" api
  def gimmeSafeValue[T](value: T): SafeValue[T] = SafeValue(value)

  val safeString: SafeValue[String] = gimmeSafeValue("Scala is awesome")
  // extract
  val string = safeString.get
  // transform
  val upperString = string.toUpperCase()
  // wrap
  val upperSafeString = SafeValue(upperString)
  // ExtractTransformWrap = ETW

  // compressed:
  val upperSafeString2 = safeString
    .flatMap(s => SafeValue(s.toUpperCase()))

  /*
  Monad:
  1. Ability to wrap a value (constructor = pure or unit)
  2. Ability to transform from one safe value
    into another safe value
   */

  // Examples

  // Example 1: census
  case class Person(firstName: String, lastName: String) {
    assert(firstName != null && lastName != null)
  }

  // census API
  def getPerson(firstName: String, lastName: String): Person =
    if (firstName != null){
      if(lastName != null) {
        Person(firstName, lastName)
      } else {
        null
      }
    } else {
      null
    }

  def getPersonBetter(firstName: String, lastName: String): Option[Person] =
    Option(firstName).flatMap { fName =>
      Option(lastName).flatMap { lName =>
        Option(Person(fName, lName))
      }
    }

  def getPersonBetterFor(firstName: String, lastName: String): Option[Person] =
    for {
      fName <- Option(firstName)
      lName <- Option(lastName)
    } yield Person(fName, lName)

  // Example 2: asynchronous fetches

  case class User(id: String)
  case class Product(sku: String, price: Double)

  def getUser(url: String): Future[User] = Future {
    User("daniel") // sample application
  }

  def getLastOrder(userId: String): Future[Product] = Future {
    Product("123.456", 99.99)
  }

  val danielsUrl = "my.store.com/users/daniel"

  // ETW
  getUser(danielsUrl).onComplete {
    case Success(User(id)) =>
      val lastOrder = getLastOrder(id)
      lastOrder.onComplete {
        case Success(Product(sku, p)) =>
          val vatIncludedPrice = p * 1.19
          // pass it on - send email
      }
  }

  val vatIncludedPrice: Future[Double] = getUser(danielsUrl)
    .flatMap(user => getLastOrder(user.id))
    .map(_.price * 1.19)

  val vatIncludedPrice2: Future[Double] = for {
    user <- getUser(danielsUrl)
    order <- getLastOrder(user.id)
  } yield order.price * 1.19

//  println(s"vatIncludedPrice: $vatIncludedPrice")
//  println(s"vatIncludedPrice2: $vatIncludedPrice2")

  // Example 3: double for loops

  val numbers = List(1, 2, 3)
  val chars = List('a', 'b', 'c')

  // flatMaps
  val checkerboard = numbers
    .flatMap(number => chars.map(char => (number, char)))
  val checkerboard2 = for {
    number <- numbers
    char <- chars
  } yield (number, char)

//  println(s"checkerboard: $checkerboard")
//  println(s"checkerboard2: $checkerboard")

  // Properties

  // prop 1: Left Identity
  def twoConsecutive(x: Int) = List(x, x + 1)
  twoConsecutive(3) // List(3, 4)
  List(3).flatMap(twoConsecutive) // List(3, 4)

  // Monad(c).flatMap(f) == f(x)

  // prop 2: Right Identity
  List(1, 2, 3).flatMap(x => List(x)) // List(1, 2, 3)

  // Monad(x).flatMap(x => Monad(x)), USELESS returns Monad(v)

  // prob 3: Associativity = ETW - ETW
  val incrementer = (x: Int) => List(x, x + 1)
  val doubler = (x: Int) => List(x, 2 * x)

  val dblInc: List[Int] = numbers
    .flatMap(incrementer)
    .flatMap(doubler)
  val dblInc2: List[Int] = numbers
    .flatMap(x => incrementer(x)
      .flatMap(x => doubler(x)))

  println(s"dblInc: $dblInc")
  println(s"dblInc2: $dblInc2")
  // dblInc: List(1, 2, 2, 4,   2, 4, 3, 6,   3, 6, 4, 8)
  /*
    List(
    incrementer(1).flatMap(doubler) -- 1, 2, 2, 4
    incrementer(2).flatMap(doubler) -- 2, 4, 3, 6
    incrementer(3).flatMap(doubler) -- 3, 6, 4, 8
    )

    Monad(v).flatMap(f).flatMap(g)
    ==
    Monad(v).flatMap(x => f(x).flatMap(g))
   */


}
