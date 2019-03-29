package starter.traits

/** Trait to define how to filter data
  *
  * @tparam Input  - type of data to be filtered
  * @tparam Output - type of returned data
  */
trait Filter[Input, Output] {

  /** function that will do the filtering
    *
    * @param data - data to be filtered
    * @return - filtered data
    */
  def filter(data: Input): Output
}
