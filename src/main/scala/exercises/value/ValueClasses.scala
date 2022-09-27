package exercises.value

object ValueClasses {

  // online store
  case class Product(code: String, description: String)

  trait Backend {
    def findByCode(code: String): Option[Product]
    def findByDescription(description: String): List[Product]
  }

  val aCode = "1-1234"
  val aDesc = "Foam mattress"

  val aBackend = new Backend {
    override def findByCode(code: String): Option[Product] = ???
    override def findByDescription(description: String): List[Product] = ???
  }

  // OK
  aBackend.findByCode(aCode)
  aBackend.findByDescription(aCode) // compiles but fails at runtime

  // problem.
  // 1. Use case classes
  case class BarCode(code: String)
  object BarCode {
    def apply(code: String): Either[String, BarCode] = Either.cond(
    code.matches("\\d-\\d{4}"),
    new BarCode(code),
      s"Code is Invalid: $code"
    )
  }
  case class Description(description: String)

  trait BackendV2 {
    def findByCode(code: BarCode): Option[Product]
    def findByDescription(description: Description): List[Product]
  }

  val aBackendV2: BackendV2 = new BackendV2 {
    override def findByCode(code: BarCode): Option[Product] = ???
    override def findByDescription(description: Description): List[Product] = ???
  }

//  aBackendV2.findByCode(BarCode(aCode))
//  aBackendV2.findByDescription(Description(aCode))

  // 2 - Value Classes (VCs)
  case class BarCodeVC(code: String) extends AnyVal {
    def countryCode: Char = code.charAt(0)
  }

  // - no runtime overhead
  /*
  Restrictions on VCs:
  - only ONE val constructor argument
  - no other vals inside
  - cannot be extended
  - can only extend "universal traits" (traits with
  just defs and without initialisation)
   */

  // d1
  def show[T](arg: T): String = arg.toString
  show(BarCodeVC("1-1234")) // BarCodeVC will be instantiated

  // d2 with arrays
  val barcodes = Array[BarCodeVC](BarCodeVC("1-1234"))

  // d3 - pm
  BarCodeVC("1-1234") match {
    case BarCodeVC(code) => println(code)
  }

  //
}
