Fibonacci Implementations
---

# Overview

Implement Fibonacci sequence function 
in different ways using Scala.

Inspired from 
(here)[https://dariocarrasquel.com/2016/08/26/5-ways-to-solve-fibonacci-in-scala-tail-recursion-memoization-the-pisano-period-more/]

# Implementation Cases

## Case 1: Pattern Matching

FibMatch Using Match and normal recursion
Immutable variables but non-performant.

    printLoop(40, n => fibMatch(n))

    102334155) 4922 millis

## Case 2: Loop

FibLoop Using While Loop. 
Performant but mutable variables.

    printLoop(100,n => fibLoop(n))

    354224848179261915075) 62 millis

## Case 3: Tail Recursion

FibTailRec using tail recursion. 
Performant and immutable variables.
Textbook solution for scala. 

    printLoop(100, n => fibTailRec(n))

    354224848179261915075) 67 millis

    printLoop(1000, n => fibTailRec(n))

    ...
    9928516003704476137795166849228875) 
    116 millis

    printLoop(10000, n => fibTailRec(n))

    ...
    62976171121233066073310059947366875) 
    5898 millis

## Case 4: Lazy List with Memoization

FibStream using LazyList and Memoization
Very performant and good functional but 
not so easy to understand.

    printLoop(100, n => fibLazyList(n) )
    
    354224848179261915075) 61 millis

    printLoop(100, n => memoFibLL(n) )

    354224848179261915075) 6 millis

    printLoop(1000, n => memoFibLL(n) )

    ...
    9928516003704476137795166849228875) 
    49 millis

    printLoop(10000, n => memoFibLL(n) )

    ...
    4976171121233066073310059947366875) 
    1254 millis

## Extra Case: The Pisano Period

Gets the last n digits of the Fibonacci 
sequence with tail recursion 
(6 for this example).

    printLoop(100,n => fibPisano(n))

    915075) 65 millis

    printLoop(1000,n => fibPisano(n))

    228875) 90 millis

    printLoop(10000,n => fibPisano(n))

    366875) 264 millis

# The name Fibonacci

The nth Pisano Period, written π(n), is 
the period with which the sequence of 
Fibonacci numbers taken modulo n repeats. 
Pisano periods are named after 
Leonardo Pisano, better known as Fibonacci. 
This name was made up in 1838 by the 
Franco-Italian historian Guillaume Libri 
and is short for filius Bonacci 
(‘son of Bonacci’).