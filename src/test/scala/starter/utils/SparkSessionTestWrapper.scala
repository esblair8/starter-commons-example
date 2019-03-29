package starter.utils

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

trait SparkSessionTestWrapper {

  def initialiseTestSparkSession(name: String): SparkSession = {
    SparkSession
      .builder
      .master("local")
      .enableHiveSupport
      .config(createSparkConf())
      .appName(name)
      .getOrCreate
  }

  private def createSparkConf(): SparkConf = new SparkConf()
    .set("spark.sql.warehouse.dir", "/tmp/hive/warehouse")
    .set("spark.sql.catalogImplementation", "hive")
    .set("spark.hadoop.hive.metastore.warehouse.dir", "/tmp/hive")
    .set("spark.local.dir", "/tmp/spark-temp")
    .set("spark.worker.cleanup.enabled", "true")
}
