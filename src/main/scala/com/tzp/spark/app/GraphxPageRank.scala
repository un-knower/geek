package com.tzp.spark.app

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.graphx.{GraphLoader, GraphXUtils}

/**
  * author i
  * date 2016/7/12
  */
object GraphxPageRank {
  def main(args: Array[String]){
    val conf = new SparkConf().setAppName("PageRank").setMaster("local[2]")
    GraphXUtils.registerKryoClasses(conf)
    val sc = new SparkContext(conf)
    val graph = GraphLoader.edgeListFile(sc, "G:\\idea\\geek\\src\\main\\resources\\data\\followers.txt")
    // Run PageRank
    val ranks = graph.pageRank(0.00001).vertices
    // Join the ranks with the usernames
    val users = sc.textFile("G:\\idea\\geek\\src\\main\\resources\\data\\users.txt").map { line =>
      val fields = line.split(",")
      (fields(0).toLong, fields(1))
    }
    val ranksByUsername = users.join(ranks).map {
      case (id, (username, rank)) => (username, rank)
    }
    // Print the result
    println(ranksByUsername.collect().mkString("\n"))
  }
}
