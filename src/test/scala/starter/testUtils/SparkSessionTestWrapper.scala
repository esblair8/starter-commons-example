package starter.testUtils

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession


/**
  * Wrapper to create a  Spark Session for Unit Testing
  * To be called when a Spark App starts.
  * Spark Session must be stopped when data transformation has finished
  */
trait SparkSessionTestWrapper {

  /** Creates a Test Spark Session Object
    *
    * @param appName - name of application - usually spark app entry point class name
    * @return - SparkSession
    */
  def initialiseTestSparkSession(appName: String): SparkSession = {
    SparkSession
      .builder
      .master("local")
      .enableHiveSupport
      .config(createSparkConf())
      .appName(appName)
      .getOrCreate
  }

  /** Set some spark configuration
    *
    * @return - SparkConf
    */
  private def createSparkConf(): SparkConf = new SparkConf()
    .set("spark.sql.warehouse.dir", "/tmp/hive/warehouse")
    .set("spark.sql.catalogImplementation", "hive")
    .set("spark.hadoop.hive.metastore.warehouse.dir", "/tmp/hive")
    .set("spark.local.dir", "/tmp/spark-temp")
    .set("spark.worker.cleanup.enabled", "true")
}
