package ruleEngines

import actors.ActorsExecutor
import util.RuleUtil._

import scala.util.{Failure, Success, Try}

object EN_RuleEngine {

  def execute(signalDataMap : Map[String, String], filePath : String) = {
    val rules = loadRule(filePath)
    val signalRules = rules.get(signalDataMap.get("signal").get).get.filter(rec => rec.valueType == signalDataMap.get("value_type").get)
    val valueToCompare = signalDataMap.get("value").get
    signalRules.toList match {
      case List() => println("No rule found for signal : "+signalDataMap.getOrElse("signal",null))
      case x :: xs => {
        for(rule <- signalRules) {
          Try(ActorsExecutor.executeAction(valueToCompare,rule)) match{
            case Success(data) => data.toString match {
              case "false" => println(signalDataMap)
              case "true" => println()
           }
            case Failure(ex) => println(valueToCompare+" value is not compatible as per specified value type "+rule.valueType)
          }
        }
      }
    }
  }
}
