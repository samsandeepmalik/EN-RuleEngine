/**
  * This object class execute data type actors(operations) on the basis of signal "value_type".
  * executeAction method will take parameters like RuleInfo and signal value for evaluation. After evaluation
  * it'll return result in Boolean
  */
package actors

import model.RuleInfo
import util.RuleUtil

object ActorsExecutor {

  def executeAction(valueToCompare: Any, rule: RuleInfo) : Boolean = rule.valueType match {
    case "Integer" => NumericActor.eval(rule.operatorAction,valueToCompare.asInstanceOf[String].toInt, rule.value.toInt)
    case "String"  => StringActor.eval(rule.operatorAction,valueToCompare.asInstanceOf[String], rule.value)
    case "DateTime"=> { DateTimeActor.eval(rule.operatorAction,
        RuleUtil.parseDateStr(valueToCompare.asInstanceOf[String],rule.additionalInfo.asInstanceOf[String]),
        RuleUtil.parseDateStr(rule.value,rule.additionalInfo.asInstanceOf[String]))
    }
    case _ => throw new Error(rule.valueType + " is not valid value type.")
  }

}
