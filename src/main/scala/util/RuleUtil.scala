package util

import java.io.{File, FileInputStream}
import java.text.SimpleDateFormat
import model.RuleInfo
import org.yaml.snakeyaml.Yaml
import scala.collection.JavaConversions._
import scala.util.{Failure, Success, Try}

object RuleUtil {
/*
   def main(args: Array[String]): Unit = {
      val rules = loadRule("C:\\workRelatedData\\En_Rule_Engine\\config\\rules.yaml")
     println(rules)
   }*/

   implicit lazy val loadRule = (ruleFilePath : String) => {
     val yaml = new Yaml
     val data = yaml.loadAs(new FileInputStream(new File(ruleFilePath)),
       classOf[java.util.LinkedHashMap[String, java.util.ArrayList[java.util.LinkedHashMap[String, String]]]])
     data.map { rec => (rec._1, rec._2.map(r => RuleInfo(r.get("name"), r.get("value"), r.get("valueType"), r.get("action"),r.get("additionalInfo")))) }
   }

 def parseDateStr(dateStr : String, pattern : String) = {
    val dateFormat = new SimpleDateFormat(pattern)
    Try(dateFormat.parse(dateStr)) match {
      case Success(date) => date
      case Failure(ex) => throw new Error(ex.getMessage)
    }

  }
}
