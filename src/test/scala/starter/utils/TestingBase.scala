package starter.utils

import com.github.mrpowers.spark.fast.tests.DataFrameComparer
import org.apache.spark.sql.SparkSession
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FunSuite}
import org.slf4j.{Logger, LoggerFactory}

import scala.io.Source
import scala.reflect.io.Path
import scala.util.Try

trait TestingBase extends FunSuite
  with SparkSessionTestWrapper
  with DataFrameComparer
  with BeforeAndAfter
  with BeforeAndAfterAll {

  lazy val log: Logger = LoggerFactory.getLogger(getClass)
  import log._
  implicit val spark: SparkSession = initialiseTestSparkSession(this.getClass.getSimpleName)

  def createTable(createTableScript: String) {
    val scriptStream = getClass.getResourceAsStream("/creat_input_table.hql")
    val createInputTableScript = Source.fromInputStream(scriptStream).getLines.mkString("\n").dropRight(1)
    spark.sql(createInputTableScript)
  }

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
