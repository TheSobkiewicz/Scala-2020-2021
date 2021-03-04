object task2 {
  abstract class Money{def name:String}
  abstract class PolishMoney extends Money {}
  abstract class EnglishMoney extends  Money {}
  class Grosz extends PolishMoney{def name ="grosz"}
  class Zlotowka extends PolishMoney{def name="Zloty"}
  class Penny extends EnglishMoney {def name=" Penny"}
  class Pound extends EnglishMoney{def name="Pound"}
  abstract class Cashieer
  {
    type proper<:Money
    def getPayment(g:proper)
  }
  class PolishCashieer(value:String) extends  Cashieer
  {
    type proper=PolishMoney
      def getPayment(g:proper){println(s"Polish Cashieer $value get payment of ${g.name}")}
  }
  class EnglishCashieer(value:String) extends Cashieer
  {
    type proper=EnglishMoney
    def getPayment(g: EnglishMoney){ println(s"English Cashieer $value get payment of ${g.name} ") }
  }
  def main(args: Array[String]): Unit = {
    val g = new Grosz
    val z = new Zlotowka

    val pe = new Penny
    val po = new Pound
    val polishCashieer = new PolishCashieer("Ania")
    val englishCashieer = new EnglishCashieer("John")

    polishCashieer.getPayment(g)
    polishCashieer.getPayment(z)

    //polishCashieer.getPayment(pe) // when uncommented - compilation error
    // polishCashieer.getPayment(po) // when uncommented - compilation error

    englishCashieer.getPayment(pe)
    englishCashieer.getPayment(po)

    // englishCashieer.getPayment(g) // when uncommented - compilation error
    // englishCashieer.getPayment(z) // when uncommented - compilation error
  }

}
