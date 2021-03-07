package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.log4j._

object TotalAmountSpendHman {


  def parseLine(line:String): (Int, Float) = {
    val fields = line.split(",")
    val custID = fields(0).toInt
    val amount = fields(2).toFloat
    (custID, amount)
  }

  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]", "TotalAmountSpendHman")

    val input = sc.textFile("data/customer-orders.csv")


    val rddMap = input.map(parseLine)

    //sorted by customer id
    val totalByCustomer = rddMap.reduceByKey( (x,y) => (x+y))
    val sortedByCustId = totalByCustomer.sortByKey()

    val result1 = totalByCustomer.collect()

    result1.foreach(println)

    println("Sorted by customer amount spend")
    //sort by amount
    val flipped = totalByCustomer.map(x => (x._2, x._1))
    val sortedbyAmount = flipped.sortByKey()

    val result2 = sortedbyAmount.collect()

    result2.foreach(println)


  }

}
