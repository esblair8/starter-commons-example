package starter

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkSessionWrapper {

  def createSparkSession(appName: String): SparkSession = {
    SparkSession.builder
      .appName(appName)
      // .config(createSparkConf())
      .enableHiveSupport
      .getOrCreate
  }

  private def createSparkConf(): SparkConf = new SparkConf()
    .set("spark.sql.catalogImplementation", "hive")

}
