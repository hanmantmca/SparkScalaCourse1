package com.sundogsoftware.spark

object learningScala {

  def main(args: Array[String]): Unit = {


    println("Hello World !!!")



    val picard: String = "Picard"
    val bestPicard: String = "Picard"

    val isMat:Boolean = picard == bestPicard
    println(" find "+ isMat)


    //Fibonacci series
    var i: Int = 0
    var j: Int = 1
    var l: Int = 0

    print(i +" "+ j + " ")
    for (k <- 0 to 15 ){
      l = i + j
      print(l + " ")
      i = j
      j = l
    }

  }

}
