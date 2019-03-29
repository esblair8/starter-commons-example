package starter.traits

/** Trait to define how data should be mappped
  *
  * @tparam Input  - type of input data
  * @tparam Output - type of data returned after mapping has been applied
  */
trait Mapper[Input, Output] {

  /** Function that will map data
    *
    * @param data - data to be mapped
    * @return - data after it has been mapped
    */
  def map(data: Input): Output
}
