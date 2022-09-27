package exercises.shuffle

import scala.util.Random

object Poker extends App {

  case class Player(name: String, hand: List[Card])

  def deal(playersCount: Int,
            handCount: Int,
            deck: List[Card]):
            (List[Player], List[Card]) = {

    var currentDeck = deck
    val players = (1 to playersCount)
      .map(playerNumber => {
        val player = Player(s"Player $playerNumber",
          currentDeck.take(handCount))
        currentDeck = currentDeck.drop(handCount)
        player
      }).toList

    (players, currentDeck)
  }



  val playersCount = 4
  val handCount = 5
  val fullDeck = Random.shuffle(Cards.createDeck().toList)
  val (players, remainingDeck) = deal(playersCount, handCount, fullDeck)

  players.foreach(println(_))
  println(s"Remaining cards: ${remainingDeck.size}")
}
