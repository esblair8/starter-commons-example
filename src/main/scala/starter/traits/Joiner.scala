package starter.traits

/** Trait to defined how data should be joined
  *
  * @tparam Input  - type of data to be joined
  * @tparam Output - type of returned data
  */
trait Joiner[Input, Output] {

  /** Function to join two data sources
    *
    * @param left  - data to be joined
    * @param right - data to be joined
    * @return - data after it has ben joined
    */
  def join(left: Input, right: Input): Output
}
