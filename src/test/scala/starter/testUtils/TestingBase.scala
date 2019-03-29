package starter.testUtils

import com.github.mrpowers.spark.fast.tests.DataFrameComparer
import org.apache.spark.sql.SparkSession
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FunSuite}
import org.slf4j.{Logger, LoggerFactory}

import scala.io.Source
import scala.reflect.io.Path
import scala.util.Try

/**
  * Trait that can be extended by any Spark Testing Class
  * Provides a SparkSession for Testing and some helper functions
  */
trait TestingBase extends FunSuite
  with SparkSessionTestWrapper
  with DataFrameComparer
  with BeforeAndAfter
  with BeforeAndAfterAll {

  lazy val log: Logger = LoggerFactory.getLogger(getClass)

  import log._

  implicit val spark: SparkSession = initialiseTestSparkSession(this.getClass.getSimpleName)

  /** Created a Hive table on local hdfs
    * Add create table script to main resources and pass in  file name as param
    *
    * @param createTableScript - location of create table script name - usually stored in main resources
    *
    */
  def createTable(createTableScript: String) {
    val scriptStream = getClass.getResourceAsStream(createTableScript)
    val createInputTableScript = Source.fromInputStream(scriptStream).getLines.mkString("\n").dropRight(1)
    spark.sql(createInputTableScript)
  }

  /** Drop a Hive Table with supplied name
    *
    * @param tableName - table to be dropped
    */
  def dropTable(tableName: String) {
    spark.sql(s"drop table $tableName")
  }

  private def deleteTempHiveDir() {
    val dirPath = "/tmp/hive"
    info("deleting temp directroy")
    val path: Path = Path(dirPath)
    val result: Try[Boolean] = Try(path.deleteRecursively)
    if (result.isSuccess) debug(s"$dirPath deleted")
    else info(s"$dirPath already deleted")
  }

  override def afterAll() {
    super.afterAll()
    spark.stop()
    deleteTempHiveDir()
  }


}
