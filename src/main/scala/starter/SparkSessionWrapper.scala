package starter

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Wrapper to create a Spark Session.
  * To be called when a Spark App starts.
  * Spark Session must be stopped when data transformation has finished
  */
object SparkSessionWrapper {

  /** Creates a Spark Session Object
    *
    * @param appName - name of application - usually spark app entry point class name
    * @return - SparkSession
    */
  def createSparkSession(appName: String): SparkSession = {
    SparkSession.builder
      .appName(appName)
      .config(createSparkConf())
      .enableHiveSupport
      .getOrCreate
  }

  /** Set some spark configuration
    *
    * @return - SparkConf
    */
  private def createSparkConf(): SparkConf = new SparkConf()
    .set("spark.sql.catalogImplementation", "hive")

}
