package com.sundogsoftware.spark

import scala.collection.immutable._

object  LearningFunctional{


  def square(x: Int): Int = {
    x * x
  }

  def cubeIt(x: Int): Int = {
    x * x * x
  }

  def tranformIt(x: Int, f: Int => Int): Int ={
    f(x)
  }

  def main(args: Array[String]): Unit = {

    println("Sqare " + square(3))

    val result = tranformIt(4, square)
    println("Transform " + result)


    //List
    val shipList = List("ABC", "BCD", "CDE", "DEF")

    for(ship <- shipList)(println(ship))

    println("Reverse the each word characters")
    val backwordShip = shipList.map( (ship: String) => {ship.reverse })

    for(ship <- backwordShip  )(println(ship))

    //reduce() - combine all the collection items1032247890

    val numList = List(1, 2, 3, 4, 5)

    val res = numList.reduce((x,y) => x+y)
    println("Sum of elements "+ res)

  }

}
