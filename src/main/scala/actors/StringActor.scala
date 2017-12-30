/**
  * This class evaluate defined String operation on left(value getting from signal) and right(value defined in rule)
  * operand then return result in Boolean value.
  * String operation action as follows:
  * - equal_to : compare 2 strings(signal value and rule value) are equal
  * - starts_with : check a signal value start with the value defined in rule
  * - ends_with : check signal value ends with the value defined in rule
  * - contains : check signal value contains the value defined in rule
  * - non-empty : check signal value is not empty
  * - matches_regex : signal value validate against regex defined in rule
  * If operatorType is invalid then it'll throw ERROR
  */
package actors

object StringActor {

 def eval(operatorType: String, leftOpd: String, rightOpd: String): Boolean = operatorType match {
   case "equal_to"  => leftOpd.equals(rightOpd)
   case "starts_with" => leftOpd.startsWith(rightOpd)
   case "ends_with"  => leftOpd.endsWith(rightOpd)
   case "contains" => leftOpd.contains(rightOpd)
   case "non_empty" => !leftOpd.isEmpty
   case "matches_regex" => leftOpd.matches(rightOpd)
   case _ => throw new Error(operatorType + " is not valid operation type on String data type.")
  }

}
