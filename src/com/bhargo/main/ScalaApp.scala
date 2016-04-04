package com.bhargo.main

import com.bhargo.enums.Fruit
import com.bhargo.domain._
/**
 * Created by barya on 4/4/16.
 */
object ScalaApp {
  def main(args: Array[String]) {
    println(add(2, 3))
    println(partialAdd2(5))
    println(callCurriedAdd(9))
    println(curriedAdd(5)(5))
    println(uncurriedAdd(7, 7))
    //varArgs("This","is","a","variable","args","example")

    //Random.nextInt(5) returns a random num till 4,Seq.fill(25) creates a list of 25
    //val numList = Seq.fill(25)(Random.nextInt(5))
    //println(numList)

    listToMap(createList())
    createAndTraverseArray()
    createAndTraverseSet()

    val vendingMachine:VendingMachine[SoftDrink] = new VendingMachine[SoftDrink]()
    val soda:Soda = new Soda()
    //var drinkList:List[SoftDrink] = List()
    //drinkList = (vendingMachine.add(new SoftDrink()) ++ vendingMachine.add(soda))
    //println("size of the list is " + drinkList.size)

    vendingMachine.addDrink(soda,5)
    vendingMachine.addDrink(new SoftDrink(), 7)
    println("The current count is " + vendingMachine.getInverntoryCount())

    val list:List[Soda] = List(new Soda())

    //fix this
    patternMatch(list)
    //fix this
    /*funcReturningOption("Demo")*/
  }

  //function for Option Demo
  /*def funcReturningOption(x:String):Option[Int]= {
    if(x != null) {
        x.length

    } else {

    }
  }*/

  //val pattern1:List[VendingMachine[Water]] = List()


  //find a valid example of patten matching
  def patternMatch(list:List[SoftDrink]):Unit = list match {
    case List() => println("a list of VendingMachine")
      val soda = new Soda()
    case List(soda) => println("Soda")
    case _ => println("default")
  }



  def createAndTraverseSet() {
    var set: Set[Int] = Set()
    //adding to the set
    set += 3
    println(set.size)

    //deletion while traversal
    set.foreach(
       n => if (n == 3)
         //removing from the set
         set -= n
       )

    println(set.size)
  }

  def createAndTraverseArray() {
    val intArr: Array[Int] = new Array[Int](5)
    //adding to the array
    intArr(0) = 55
    intArr(1) = 86
    intArr(2) = 77
    intArr(3) = 7
    intArr(4) = 37
    var temp: Int = 0

    //bubble sort
    for (i <- 0 until intArr.length) {
      for (j <- (i + 1) until intArr.length) {
        if (intArr(i) > intArr(j)) {
          temp = intArr(i)
          intArr(i) = intArr(j)
          intArr(j) = temp
        }
      }
    }
    for (i <- 0 until intArr.length) {
      print(intArr(i) + " ")
    }
    println()

  }

  def createList(): List[Fruit.fruit] = {
    //an empty list
    //var fruitList = List[Fruit.fruit]()
    var fruitList: List[Fruit.fruit] = List()

    //adding to a list
    for (i <- 0 until 10000) {
      if (i % 4 == 0) {
        //adding to a list
        fruitList ::= Fruit.MANGO
      } else if (i % 3 == 0) {
        fruitList ::= Fruit.BANANA
      } else if (i % 2 == 0) {
        fruitList ::= Fruit.APPLE
      } else {
        fruitList ::= Fruit.KIWI
      }
    }
    println(fruitList.length)
    fruitList
  }

  def listToMap(list: List[Fruit.fruit]) = {
    var finalMap: Map[Fruit.fruit, Int] = Map()
    var index = -1
    list.foreach(
      i => {
        index += 1
        if (i.eq(Fruit.APPLE)) {
          //removing from a list
          list.drop(index)
        }
        else if (finalMap.contains(key = i)) {
          //adding to a map
          finalMap += (i -> (finalMap(i) + 1))
        } else {
          finalMap += (i -> 1)
        }
      }
    )
    finalMap.keySet.foreach(n => println("Count for fruit " + n + " is " + finalMap(n)))

    //deletion from the map while traversing, like using itr.remove in java
    finalMap.keySet.foreach(n => if (n.eq(Fruit.APPLE)) {
      //removing from the map
      finalMap -= n
    })

    println("Size after deletion is " + finalMap.size)
  }

  def add(a: Int, b: Int): Int = a + b

  //This is a partial function
  def partialAdd2 = add(2, _: Int)

  //currying an uncurried function, also as def curriedAdd = (add _).curried
  def curriedAdd: (Int) => (Int) => Int = (add _).curried

  //a curried function
  def curriedAdd2(a: Int)(b: Int): Int = a + b

  //uncurring a curried function
  val uncurriedAdd = Function.uncurried(curriedAdd2 _)

  def callCurriedAdd: (Int) => Int = curriedAdd(2)

  //variable arguments
  def varArgs(args: String*) = args.foreach(println)

}

