package starter.traits

/** Trait to define how to map a dataframe (usually for group bys)
  *
  * @tparam Input  - Type of input data
  * @tparam Output - Type of data t be returned
  */
trait DataFrameMapper[Input, Output] {

  /** Function that will map data
    *
    * @param data - data to be mapped
    * @return - data after it has been mapped
    */
  def map(data: Input): Output
}
