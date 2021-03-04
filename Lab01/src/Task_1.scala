trait Engine
{
  type proper<:fuel
  def addFuel(a:proper)
}
class Car{}
class SUV{}
abstract class fuel{
 def amount:Int
  def Type:String
}
class ElectricCharges(val amount:Int) extends  fuel
{
  override def Type: String = "AH"
}
class Oil(val amount:Int) extends  fuel
{
  override def Type: String = "Liter"
}
class HGas(val amount:Int) extends  fuel
{
  override def Type: String = "H2 Liter"
}
trait ElectricMotor extends Engine
{
  type proper=ElectricCharges
  override def addFuel(a: ElectricCharges){
    println("Added "+a.amount+a.Type)
  }
}
  trait Diesel extends Engine
  {
    type proper=Oil
    override def addFuel(a: Oil){
      println("Added "+a.amount+a.Type)
    }
    }
    trait Hydro extends Engine
    {
      type proper=HGas
      override def addFuel(a: HGas){
        println("Added "+a.amount+a.Type)
      }
      }


object cars extends App {
  val tesla = new Car with ElectricMotor;
  val vw = new Car with Diesel;
  val honda = new SUV with Hydro;
  tesla.addFuel( new ElectricCharges(100) ) // this prints, added 100 Ah
  vw.addFuel( new Oil(30)) // should print added 300 Liter
  honda.addFuel( new HGas(10) ) // should print added 10 H2 Liter
  // any other combination should not compile, specifically
  //vw.addFuel( new ElectricCharges(10) ) // should not compile
}