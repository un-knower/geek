package com.tzp.spark.phoenix

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.phoenix.spark._

/**
  * author i 
  * date 2016/7/13 17:04
  */

object SparkPhoenix {

  case class User(ID: Int, Idfa: String, idfv: String, Mac: String, Udid: String, OpenUdid: String, Model: String,
                   Ios: String, Cid: String, Toolversion: String,ToolType: String,Serialnumber: String,Tokennum: String,
                    Bundleid: String, Certificateid: String, PressTime: String, LastAcceccTime: String)
  def main(args: Array[String]) {

    val conf = new SparkConf().setMaster("local[4]").setAppName("USERS")
    val sc = new SparkContext(conf)
    val s = System.currentTimeMillis()
    val u = sc.textFile("E:\\data\\1.txt")
      .map(_.split(";"))
      .filter(line=>line.length==17)
      .map(a=>User(a(0).toInt,a(1),a(2),a(3),a(4),a(5),a(6),a(7),a(8),a(9),a(10),a(11),a(12),a(13),a(14),a(15),a(16)))
      .saveToPhoenix(
        "T_USERS_MBL",
        Seq("ID","IDFA","IDFV","MAC","UDID","OPENUDID","MODEL","IOS","CID","TOOLVERSION","TOOLTYPE","SERIALNUMBER",
          "TOKENNUM","BUNDLEID","CERTIFICATEID","PRESSTIME","LASTACCESSTIME"),
        zkUrl = Some("master:2181")
      )
    val e = System.currentTimeMillis()
    println(e-s)
  }


}
