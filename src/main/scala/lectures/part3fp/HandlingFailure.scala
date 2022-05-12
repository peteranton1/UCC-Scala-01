package lectures.part3fp

import scala.util.{Failure, Success, Try}

object HandlingFailure extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Super Failure"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("A Failure")

  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  val anotherPotentialFailure = Try {
    Integer.parseInt("A")
  }
  println(anotherPotentialFailure)

  // utilities
  println(potentialFailure.isSuccess)
  println(potentialFailure.isFailure)

  def backupMethod(): String = "A valid result"
  println(potentialFailure
    .orElse(Try(backupMethod())))

  // if you design the api
  def betterUnsafeMethod(): Try[String] =
    Failure(new RuntimeException("better unsafe"))

  def betterBackupMethod(): Try[String] =
    Success("a better result")

  val betterFallback = betterUnsafeMethod()
            .orElse(betterBackupMethod())
  println(betterFallback)
}
