/**
  * This class evaluate defined Numeric operation on left(value getting from signal) and right(value defined in rule)
  * operand then return result in Boolean value.
  * Numeric operation action as follows:
  * - equal_to : validate signal value(Int) is equal to rule value(Int).
  * - greater_than : validate signal value(Int) is greater than rule value(Int).
  * - less_than : validate signal value(Int) is less than rule value(Int).
  * - greater_than_or_equal_to : validate signal value(Int) is greater than or equal to rule value(Int).
  * - less_than_or_equal_to : validate signal value(Int) is less than or equal to rule value(Int).
  * If operatorType is invalid then it'll throw ERROR
  */
package actors

object NumericActor {

  def eval(operatorType : String, leftOperand : Int, rightOperand : Int) : Boolean = operatorType match {
        case "equal_to" => (leftOperand == rightOperand)
        case "greater_than" => (leftOperand > rightOperand)
        case "less_than" => (leftOperand < rightOperand)
        case "greater_than_or_equal_to" => (leftOperand > rightOperand || leftOperand == rightOperand)
        case "less_than_or_equal_to" => (leftOperand < rightOperand || leftOperand == rightOperand)
        case _ => throw new Error(operatorType + " is not valid operation on Numeric data.")
  }

}
