package model

case class RuleInfo (val name : String,
                      val value : String,
                      val valueType : String,
                      val operatorAction : String,
                      val additionalInfo : Any){

  override def toString: String = "RuleInfo( "+name+", "+value+", "+valueType+", "+operatorAction+", "+additionalInfo+" )"
}
