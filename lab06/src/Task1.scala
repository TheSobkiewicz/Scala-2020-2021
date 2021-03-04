object Task1 {
abstract class Mc {
    def price:Double
    def name :String
    def ifLarge : Boolean
    def checkIfLargeName(Name:String):String={
      if(ifLarge) " large "+Name
      else " normal "+Name
  }
  def checkIfLargePrice(Nprice:Double):Double={
    if(ifLarge) Nprice*1.5
    else Nprice
  }
  }
  trait BigMac extends Mc {
    abstract override def name=super.name+" with BigMac"
    abstract override def price= super.price+ 10
}
  trait McWrap extends Mc {
    abstract override def name=super.name+" with McWrap"
    abstract override def price= super.price+ 9
  }
  trait Coke extends Mc{
    abstract override def name =super.name + checkIfLargeName("coke")
    abstract override def price=super.price + checkIfLargePrice(4)
  }
  trait Fries extends Mc{
    abstract override def name =super.name +checkIfLargeName("fries")
   abstract override def price=super.price + checkIfLargePrice(5)
  }
  class NormalMcSet extends Mc
  {
    def name="McSet"
    def price= 0
    def ifLarge = false
  }
  class LargeMcSet extends Mc
  {
    def name="Large McSet"
    def price= 0
    def ifLarge= true
  }

  def main(args: Array[String]): Unit = {
    val firstMcSet = new NormalMcSet with BigMac with Fries with Coke
    val firstMcSetLarge = new LargeMcSet with BigMac with Fries with Coke
    val secondMcSet = new LargeMcSet with McWrap with Fries

    println(firstMcSet.name + " " + firstMcSet.price)
    println(firstMcSetLarge.name + " " + firstMcSetLarge.price)
    println(secondMcSet.name + " " + secondMcSet.price)


  }
}
