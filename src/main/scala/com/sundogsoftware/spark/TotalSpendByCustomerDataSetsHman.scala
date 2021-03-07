package com.sundogsoftware.spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{round, sum}

import org.apache.spark.sql.types.{DoubleType, IntegerType, StructType}

object TotalSpendByCustomerDataSetsHman {


  case class Customer(custID: Int, productId: Int, amount: Double)

  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkSession using every core of the local machine
    val spark = SparkSession
      .builder
      .appName("CustomerSpend")
      .master("local[*]")
      .getOrCreate()

    val temperatureSchema = new StructType()
      .add("custID", IntegerType, nullable = true)
      .add("productId", IntegerType, nullable = true)
      .add("amount", DoubleType, nullable = true)


    // Read the file as dataset
    import spark.implicits._
    val ds = spark.read
      .schema(temperatureSchema)
      .csv("data/customer-orders.csv")
      .as[Customer]

    val custWithAmount =
      ds
        .groupBy("custID")
        .agg(round(sum("amount"), 2)
        .alias("SumAmt"))


/*    val custwithAmountSum =
      custWithAmount
        .groupBy("custID")
        .sum("amount")*/


    // Collect, format, and print the results
    val results = custWithAmount.sort("SumAmt")

    results.show(results.count().toInt)

/*    for (result <- results) {
      val custID = result(0)
      val amount = result(1).asInstanceOf[Double]
      val formattedTemp = f"$amount%.2f F"
      println(s"$custID Spend : $formattedTemp")
    }*/
  }

}
