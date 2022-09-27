package exercises.adt

object ADTs {
  // way of structuring data

  sealed trait Weather // Sum type
  case object Sunny extends Weather
  case object Windy extends Weather
  case object Rainy extends Weather
  case object Cloudy extends Weather

  // Weather is Sunny + Windy + Rainy + Cloudy = SUM type

  def feeling(weather: Weather): String = weather match {
    case Sunny => ":)"
    case Cloudy => ":|"
    case Rainy => ":("
  }

  case class WeatherForecastRequest(latitude: Double, longitude: Double)
  // (Double, Double) => WFR
  // type WFR = Double x Double = product type

  // hybrid types
  sealed trait WeatherForecastResponse // SUM type
  case class Valid(weather: Weather)
    extends WeatherForecastResponse // PRODUCT type
  case class Invalid(error: WeatherServerError, description: String)
    extends WeatherForecastResponse // PRODUCT type

  // advantages
  // 1. Illegal states are NOT representable
  // 2. Highly composable
  // 3. immutable data structure
  // 4. just data not functionality => structure our code

  type NaiveWeather = String
  def naiveFeeling(weather: String): NaiveWeather = weather match {
    case "sunny" => ":)"
    // other cases
  }
  naiveFeeling("35 degrees") // illegal state

  // complexity of ADT = number of possible values for that ADT
  // goal: reduce complexity

  sealed trait WeatherServerError
  case object NotFound extends WeatherServerError
  // other errors ...
  
}
