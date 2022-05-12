package exercises

import scala.annotation.tailrec

class SocialNetwork {

  /*  2. Overly simplified network based on maps
    Person = String
    - Add person
    - remove
    - friend (mutual)
    - unfriend (mutual)
     */
  def addPerson(network: Map[String, List[String]])
               (name: String): Map[String, List[String]] =
    network + (name -> List())

  def addFriend(network: Map[String, List[String]])
               (name: String,
                other: String): Map[String, List[String]] = {
    def addFriendToList(friendsList: List[String], friend: String): List[String] = {
      if (friendsList.isEmpty) friendsList :+ friend
      else if (friendsList.contains(friend))
        friendsList
      else
        friendsList :+ friend
    }

    network
      .map(p => {
        if (p._1 == name)
          p._1 -> addFriendToList(p._2, other)
        else if (p._1 == other)
          p._1 -> addFriendToList(p._2, name)
        else
          p
      })
  }

  @tailrec
  final def removeUncheckedFriends(uncheckedFriends: List[String],
                                   checkedFriends: List[String],
                                   other: String): List[String] = {
    if (uncheckedFriends.isEmpty) checkedFriends
    else if (uncheckedFriends.head == other)
      removeUncheckedFriends(uncheckedFriends.tail,
        checkedFriends, other)
    else
      removeUncheckedFriends(uncheckedFriends.tail,
        checkedFriends :+ uncheckedFriends.head, other)
  }

  def removeFriend(network: Map[String, List[String]])
                  (name: String,
                   other: String): Map[String, List[String]] = {
    network
      .map(p => {
        if (p._1 == name)
          p._1 -> removeUncheckedFriends(p._2, List(), other)
        else if (p._1 == other)
          p._1 -> removeUncheckedFriends(p._2, List(), name)
        else
          p
      })
  }

  def removePerson(network: Map[String, List[String]])
                  (name: String): Map[String, List[String]] = {
    network
      .map(p => p._1 -> removeUncheckedFriends(p._2, List(), name))
      .removed(name)
  }

  /*  2. Overly simplified network based on maps
    // STATS
    - number of friends for a given person
    - person with most friends
    - people who have no friends
    - if there is a social connection between
      two people (direct and indirect)
     */

}

object SocialNetwork {
  def apply() = new SocialNetwork()
}
