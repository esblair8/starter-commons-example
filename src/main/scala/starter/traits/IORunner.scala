package starter.traits

/** Input Output Runner
  * To be used to run a series of transformations on the input data
  *
  * @tparam Input  - type of  data to be transformed
  * @tparam Output - type of returned data
  */
trait IORunner[Input, Output] {

  /** Function to run a series of transformations on input
    *
    * @param data - the data to be transformed
    * @return - data after the series of transformations has been applied
    */
  def run(data: Input): Output
}
