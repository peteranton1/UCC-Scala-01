package exercises.shuffle

class PokerHand(val ordinal: Int) {

}
object PokerHand {
  case object HighCard extends PokerHand(0)
  case object Pair extends PokerHand(1)
  case object TwoPairs extends PokerHand(2)
  case object ThreeOfAKind extends PokerHand(3)
  case object Straight extends PokerHand(4)
  case object Flush extends PokerHand(5)
  case object FullHouse extends PokerHand(6)
  case object FourOfAKind extends PokerHand(7)
  case object StraightFlush extends PokerHand(8)
  case object RoyalFlush extends PokerHand(9)
}

case class RankedHand(cards: List[Card], pokerHand: PokerHand) {
  def compare(other: RankedHand): Int = {
    0
  }
}

object PokerHands {
  /*
def best_hand(hand):
    "From a 7-card hand, return the best 5 card hand."
    return max(itertools.combinations(hand, 5), key=hand_rank)

def hand_rank(hand):
    "Return a value indicating the ranking of a hand."
    ranks = card_ranks(hand)
    if straight(ranks) and flush(hand):
        return (8, max(ranks))
    elif kind(4, ranks):#four of a kind
        return (7, kind(4, ranks), kind(1, ranks))
    elif kind(3, ranks) and kind(2, ranks):#full house
        return (6, kind(3, ranks), kind(2, ranks))
    elif flush(hand):#all cards of same suit
        return (5, ranks)
    elif straight(ranks):#cards in sequence
        return (4, max(ranks))
    elif kind(3, ranks):#3 cards of same rank
        return (3, kind(3, ranks), ranks)
    elif two_pair(ranks):#2 pairs of same rank
        return (2, two_pair(ranks), ranks)
    elif kind(2, ranks):#1 pair of same rank
        return (1, kind(2, ranks), ranks)
    else:#Nothing (High Card)
        return (0, ranks)

def card_ranks(hand):
    "Return a list of the ranks, sorted with higher first."
    ranks = ['--23456789TJQKA'.index(r) for r, s in hand]
    ranks.sort(reverse=True)
    #to handle the (A-5) straight case
    return [5, 4, 3, 2, 1] if (ranks == [14, 5, 4, 3, 2]) else ranks

def flush(hand):
    "Return True if all the cards have the same suit."
    suits = [s for r, s in hand]
    return len(set(suits)) == 1

def straight(ranks):
    """Return True if the ordered
    ranks form a 5-card straight."""
    return (max(ranks) - min(ranks) == 4) and len(set(ranks)) == 5

def kind(n, ranks):
    """Return the first rank that this hand has
    exactly n-of-a-kind of. Return None if there
    is no n-of-a-kind in the hand."""
    for r in ranks:
        if ranks.count(r) == n: return r
    return None

def two_pair(ranks):
    """If there are two pair here, return the two
    ranks of the two pairs, else None."""
    pair = kind(2, ranks)
    lowpair = kind(2, list(reversed(ranks)))
    if pair and lowpair != pair:
        return (pair, lowpair)
    else:
        return None

def test_best_hand():
    # S = Spade
    # D = Diamond
    # C = Club
    # H = Heart
    assert (sorted(best_hand("6C 7C 8C 9C TC 5C JS".split()))
            == ['6C', '7C', '8C', '9C', 'TC'])
    assert (sorted(best_hand("TD TC TH 7C 7D 8C 8S".split()))
            == ['8C', '8S', 'TC', 'TD', 'TH'])
    assert (sorted(best_hand("JD TC TH 7C 7D 7S 7H".split()))
            == ['7C', '7D', '7H', '7S', 'JD'])
    return 'test_best_hand passes'

print test_best_hand()
 */
}