package com.tzp.spark.ml

import org.apache.spark.mllib.linalg.{Vector,Vectors}
/**
  * Created by Administrator on 2016/6/1.
  */
object LocalVector {

  def main(args: Array[String]) {
    val dv : Vector = Vectors.dense(1,2,3)
    val sv1: Vector = Vectors.sparse(3, Array(0, 2), Array(1.0, 3.0))
    println(dv)
    println(sv1)
  }
}