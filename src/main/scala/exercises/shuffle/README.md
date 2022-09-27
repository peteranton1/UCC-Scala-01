Shuffle Exercise 1
---

# Part 1

This exercise requires the user to implement 
a kind of Poker game. The focus is mainly on the 
use of clean and maintainable code. There is no 
need to provide a GUI as a command line program 
will do. 

The task is to create a program which simulates 
a Poker Game with N players. A pack of 52 cards
is shuffled and dealt according to the normal
method of dealing cards to players.

Each time the program is run, the cards are 
shuffled and dealt to the specified number of 
players (default of four). The dealing of 
cards means that each player is given the next 
card from the deck until each player is holding 
five cards. In this simple program that is the 
end of dealing, and all that remains is to 
declare the winning player. 

The program lists each player's dealt hand and 
chooses a winner based on the normal Poker 
ranking rules. 

If any questions arise regarding the details 
of the task, just use your best judgement. Be
prepared to demonstrate the solution and 
explain the thought process. 

# Part 2

Modify the program to change the poker rules to 
Texas Hold-Em style. In this style, each 
player is dealt only two cards and then five 
cards are dealt face up. Then the winner is
declared using the best hand that each player
can make using five cards from their personal 
two cards plus any of the face up cards.

# Extra notes:

## Things to avoid:
1. Code duplication
2. Long methods
3. mutable data
4. deep nesting
5. throwing exceptions

## Things to use:
1. FP concepts 
   (Immutable data, Tail Recursion, etc)
2. clean code
3. Good architecture for changes
4. Handling errors gracefully

