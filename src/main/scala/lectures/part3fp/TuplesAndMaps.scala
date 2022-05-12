package lectures.part3fp

import exercises.SocialNetwork

import scala.annotation.tailrec

object TuplesAndMaps extends App {

  // tuples - finite ordered lists
  val aTuple = (2, "Hello Scala")

  println(aTuple._2)
  println(aTuple.copy(_2 = "goodbye java"))
  println(aTuple.swap)

  // maps = keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(
    ("Jim",555),
    ("JIM",9001),
    "Dan" -> 789)
    .withDefaultValue(-1)
  println("011: " + phonebook)
  println("012: " + phonebook.contains("Jim"))
  println("013: " + phonebook("Jim"))
  println("014: " + phonebook("Mary"))

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing
  println("015: " + newPhonebook)

  // functionals on maps
  println("016: " + phonebook
    .map(pair => pair._1.toLowerCase() -> pair._2))
  println("017: " + phonebook
    .filterKeys(k => k.startsWith("J")))
  println("018: " + phonebook
    .mapValues(n => "0245 " + n))
  println("019: " + phonebook.toList)
  println("020: " + List(("Daniel", 555)).toMap)
  val names = List("Bob","James","Angela",
    "Mary","Daniel","Jim")
  println("021: " + names.groupBy(name => name.charAt(0)))

  /* Exercises

  1. What would happen in the lowercase map here
      if I had 2 original entries like
      ("jim" -> 555) and ("JIM" -> 9002)

  2. Overly simplified network based on maps
  Person = String
  - Add person
  - remove
  - friend (mutual)
  - unfriend (mutual)
  - number of friends for a given person
  - person with most friends
  - people who have no friends
  - if there is a social connection between
    two people (direct and indirect)
   */

  val network0_0: Map[String, List[String]] = Map().withDefaultValue(List())
  val social = SocialNetwork()
  // add Person
  println("--- add Person ---")
  val network1_1 = social.addPerson(network0_0)("Alan")
  println(network1_1)
  val network1_2 = social.addPerson(network1_1)("Mary")
  println(network1_2)
  val network1_3 = social.addPerson(network1_2)("John")
  println(network1_3)
  val network1_4 = social.addPerson(network1_3)("Dan")
  println(network1_4)
  val network1_5 = social.addPerson(network1_4)("Dave")
  println(network1_5)
  // add friend
  println("--- add Friend ---")
  val network2_1 = social
    .addFriend(network1_5)("Alan","Mary")
  println(network2_1)
  val network2_2 = social
    .addFriend(network2_1)("Alan","John")
  println(network2_2)
  val network2_3 = social
    .addFriend(network2_2)("John","Dan")
  println(network2_3)
  val networkAll = network2_3
  // remove friend
  println("--- remove Friend ---")
  val network3_1 = social
    .removeFriend(networkAll)("Alan","Mary")
  println(network3_1)
  // remove person
  println("--- remove Person ---")
  val network4_1 = social
    .removePerson(network3_1)("Alan")
  println(network4_1)
  println("--- end ---")

  def nFriends(network: Map[String, List[String]], person: String): Int =
    if(!network.contains(person)) 0
    else network(person).size

  println(s"networkAll: $networkAll \n" +
    s"nFriends(" + "Alan" +")= " +
    nFriends(networkAll, "Alan"))

  def mostFriends(network: Map[String, List[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(s"networkAll: $networkAll \n" +
    s"mostFriends()= " +
    mostFriends(networkAll))

  def nPeopleWithNoFriends(network: Map[String, List[String]]): Int =
    network.count(pair => pair._2.isEmpty)

  println(s"networkAll: $networkAll \n" +
    s"nPeopleWithNoFriends()= " +
    nPeopleWithNoFriends(networkAll))

  def socialConnection(network: Map[String, List[String]],
                       a: String,
                       b: String): Boolean = {
    @tailrec
    def bfs(target: String,
            consideredPeople: List[String],
            discoveredPeople: List[String]
           ): Boolean = {
      println(s"bfs($target\n\t" +
        s"$consideredPeople"+
        s"$discoveredPeople"+
        ")")
      if(discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if(consideredPeople.contains(person))
          bfs(target,
            consideredPeople,
            discoveredPeople.tail)
        else bfs(target,
          consideredPeople :+ person,
          discoveredPeople.tail ++ network(person))
      }
    }
    bfs(b, List(), network(a) :+ a)
  }
  println(s"networkAll: $networkAll \n" +
    s"socialConnection(alan,mary)= " +
    socialConnection(networkAll,"Alan","Mary"))
  println(s"networkAll: $networkAll \n" +
    s"socialConnection(alan,dan)= " +
    socialConnection(networkAll,"Alan","Dan"))
  println(s"networkAll: $networkAll \n" +
    s"socialConnection(alan,dave)= " +
    socialConnection(networkAll,"Alan","Dave"))
}
