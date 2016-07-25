package com.tzp.spark.app

import java.io.{File, PrintWriter}

import org.apache.spark.{SparkConf, SparkContext}


/**
  * author i 
  * date 2016/7/21 10:18
  */
object AppInstall {

  case class Install(userId: String, model: String, appid: String, remd: String, remdorder: String, detail: String, sort: String,
                  typeId: String, adid: String, specialid: String,operatetime: String,md: String,osversion: String,
                  isjail: String, isauth: String, idfa: String)

  def main(args: Array[String]) {

    val conf = new SparkConf().setMaster("local[4]").setAppName("AppInstall")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    import sqlContext.implicits._


    val writer = new PrintWriter(new File("C:\\Users\\Administrator\\Desktop\\app.txt" ))
    val rdd = sc.textFile("E:\\home\\data\\app\\file\\5\\*.xhtml")
      .map(_.replace("_","&").replace("$","&").replace("~","&"))
      .map(_.split("&"))

    val ds = rdd.map(a=>Install(a(0),a(1),a(2),a(3),a(4),a(5),a(6),a(7),a(8),a(9),a(10),a(11),a(12),a(13),a(14),a(15))).toDF()
    ds.printSchema()
    ds.registerTempTable("install")
    val count = sqlContext.sql("select appid,count(*) as count1, count(DISTINCT userId) as count2,count(DISTINCT idfa) as count3 from install group by appid order by count(*) desc")

    count.collect().take(200).foreach(a=>{
            writer.println(a(0)+","+a(1)+","+a(2)+","+a(3))
          })
    writer.close()

//    val app = rdd.filter(line=>line.length==16)
//      .map(a=>(a(2),a(15)))
//      .distinct()
//      .map(a=>(a._1,1))
//      .reduceByKey(_+_)
//      .sortBy(x => x._2,false).collect().take(200)
//
//    app.foreach(println)
//    app.foreach(a=>{
//      writer.println(a._1+","+a._2)
//    })
//    writer.close()
    sc.stop()
  }
}
