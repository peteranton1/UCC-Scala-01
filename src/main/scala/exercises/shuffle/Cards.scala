package exercises.shuffle

class Rank(val ordinal: Int) {
  def of(suit: Suit) = Card(this, suit)
}
object Rank {
  case object Two extends Rank(0)
  case object Three extends Rank(1)
  case object Four extends Rank(2)
  case object Five extends Rank(3)
  case object Six extends Rank(4)
  case object Seven extends Rank(5)
  case object Eight extends Rank(6)
  case object Nine extends Rank(7)
  case object Ten extends Rank(8)
  case object Jack extends Rank(9)
  case object Queen extends Rank(10)
  case object King extends Rank(11)
  case object Ace extends Rank(12)
  val values = Set(Two, Three, Four, Five, Six,
    Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)
}

class Suit(val ordinal: Int)
object Suit {
  case object Clubs extends Suit(0)
  case object Diamonds extends Suit(1)
  case object Hearts extends Suit(2)
  case object Spades extends Suit(3)
  val values = Set(Clubs, Diamonds, Hearts, Spades)
}

case class Card(rank: Rank, suit: Suit) {
  override def toString: String = rank + " of " + suit

  def compare(other: Card): Int = {
    Option(other)
      .map(
        c => rank.ordinal - c.rank.ordinal
      ).getOrElse(0)
  }
}

object Cards {
  def createDeck(): Set[Card] = {
    val deck: Set[Card] = for (
      r ← Rank.values;
      s ← Suit.values
    ) yield r of s
    deck
  }
}
