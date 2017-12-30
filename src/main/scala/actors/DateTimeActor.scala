/**
  * This class evaluate defined DateTime operation on left(value getting from signal) and right(value defined in rule)
  * operand then return result in Boolean value.
  * DateTime operation action as follows:
  * - is_future : validate signal value(Date) is after or greater than current date.
  * - is_future_and_equal : validate signal value(Date) is after current date or equal to rule value(Date).
  * - is_past : validate signal value(Date) is before or less than current date.
  * - is_past_and_equal : validate signal value(Date) is before current date or equal to rule value(Date).
  * If operatorType is invalid then it'll throw ERROR
  */
package actors

import java.util.Date

object DateTimeActor {

  def eval(operatorType: String, leftOpd: Date,rightOpd: Date): Boolean = {
    val now = new Date()
    operatorType match {
      case "is_future" => leftOpd.after(now)
      case "is_future_or_equal" => leftOpd.after(now) || leftOpd.equals(rightOpd)
      case "is_past" => leftOpd.before(now)
      case "is_past_or_equal" => leftOpd.before(now) || leftOpd.equals(rightOpd)
      case _ => throw new Error(operatorType + " is not valid operation type on String data type.")
    }
  }

}
