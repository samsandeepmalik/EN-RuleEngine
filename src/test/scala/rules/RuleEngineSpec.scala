package rules

import actors.ActorsExecutor
import model.RuleInfo
import org.scalatest.{BeforeAndAfter, FunSuite}
import util.RuleUtil
import scala.collection.mutable
import scala.util.{Failure, Success, Try}

class RuleEngineSpec extends FunSuite with BeforeAndAfter{

  var rule : RuleInfo = _
  var sampleSignalMap : Map[String, String] = _
  var rules : mutable.Map[String, mutable.Buffer[RuleInfo]]= _
  val filePath = "C:\\workRelatedData\\En_Rule_Engine\\config\\rules.yaml"

  before{
    rules = RuleUtil.loadRule(filePath)
  }

  test("NumericActor : greater_than, Test case validate 'ALT1' with positive result"){
    sampleSignalMap = Map("signal" -> "ALT4", "value" -> "241", "value_type" -> "Integer")
    val signalRules = getSignalRules
    val valueToCompare = sampleSignalMap.get("value").get
    signalRules.toList match {
      case List() => println("No rule found for signal : "+sampleSignalMap.getOrElse("signal",null))
      case x :: xs => {
        for(rule <- signalRules) {
          assertResult(Success(true)){
            Try(ActorsExecutor.executeAction(valueToCompare,rule))
          }
        }
      }
    }
  }

  test("NumericActor : Test case validate 'ALT4' signal value is not compatible with value_type"){
    sampleSignalMap = Map("signal" -> "ALT4", "value" -> "test", "value_type" -> "Integer")
    val signalRules = getSignalRules
    val valueToCompare = sampleSignalMap.get("value").get
    signalRules.toList match {
      case List() => println("No rule found for signal : "+sampleSignalMap.getOrElse("signal",null))
      case x :: xs => {
        for(rule <- signalRules) {
          //val executeResult = Try(ActorsExecutor.executeAction(valueToCompare,rule))
          val exception = intercept[NumberFormatException](ActorsExecutor.executeAction(valueToCompare,rule))
          assert(exception.toString == "java.lang.NumberFormatException: For input string: \"test\"")
        }
      }
    }
  }

  test("StringActor : Test case validate 'ALT4' signal has no rule defined for given value_type") {
    sampleSignalMap = Map("signal" -> "ALT4", "value" -> "test", "value_type" -> "String")
    val signalRules = getSignalRules
    assert(signalRules.size == 0)
  }

  test("DateTimeActor : Test case validate 'ALT3' signal rule of DateTime 'is_future' action") {
    sampleSignalMap = Map("signal" -> "ALT3", "value" -> "31/12/2017 12:22:00.8765", "value_type" -> "DateTime","additionalInfo" -> "dd/MM/yyyy HH:mm:ss.SS")
    val signalRules = getSignalRules
    val valueToCompare = sampleSignalMap.get("value").get
    signalRules.toList match {
      case List() => println("No rule found for signal : "+sampleSignalMap.getOrElse("signal",null))
      case x :: xs => {
        for(rule <- signalRules) {
          assertResult(true){
            ActorsExecutor.executeAction(valueToCompare,rule)
          }
        }
      }
    }
  }

  test("DateTimeActor : Test case validate 'ALT3' signal rule of DateTime 'is_future' action with incorrect date pattern") {
    sampleSignalMap = Map("signal" -> "ALT3", "value" -> "30/12/2017 12:22:00.8765", "value_type" -> "DateTime","additionalInfo" -> "dd/MM/yyyy HH:mm")
    val signalRules = getSignalRules
    val valueToCompare = sampleSignalMap.get("value").get
    signalRules.toList match {
      case List() => println("No rule found for signal : "+sampleSignalMap.getOrElse("signal",null))
      case x :: xs => {
        for(rule <- signalRules) {
          assertResult(false){
            ActorsExecutor.executeAction(valueToCompare,rule)
          }
        }
      }
    }
  }

  def getSignalRules = {
    rules.get(sampleSignalMap.get("signal").get).get.filter(rec => rec.valueType == sampleSignalMap.get("value_type").get)
  }
}
