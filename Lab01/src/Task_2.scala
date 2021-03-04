abstract class flow(amount:Int, category :String,description:String)
{
  def amount():Int=amount
  def category():String=category
  def description():String=description

  override def toString: String =s"($amount, $category, $description)"
}
class Income(amount:Int, category :String,description:String) extends flow(amount,category ,description)
object Income
{
  def apply(amount:Int, category :String,description:String)=new Income(amount, category,description)
}
class Expense(amount:Int, category :String,description:String)extends flow(amount,category ,description)
object Expense
{
  def apply(amount:Int, category :String,description:String)=new Expense(-amount,category,description)
}
class Budget
{
  var list=List[flow]()
  def +(income: Income){list=list:+income}
  def -(expense: Expense){list=list:+expense}
  def total():Int=list.map(a=>a.amount).sum
  def expenses()=list.filter(_.amount()<0)
  def expenses(a:String)=list.filter(_.amount()<0).filter(_.category()==a)
  def incomes(a:String)=list.filter(_.amount()>0).filter(_.category()==a)
  def track()= {
    var sum=0
    var ls=List[Int]()
    for(i<-list)
      {
        sum+=i.amount()
        ls=ls:+sum
      }
      ls
  }
  def maxExpense()= {
    this.expenses().map(a=>a.amount()).min
  }
// def category()=list.map(a=>a.category)
//  def trackWith(a:()=>Any) =
//  {
//    for(i<-this.a.track())
//  }

}
object Task_2 extends App {
//  Exercise 2
//  The goal is to exercise collections functionality. We need to design classes that can keep track of our budget that is a list of incomes and expenses. We will then have a few methods useful for tracking what is going on in the budget. All of then have to be implemented with collections API.
//
//  You are free to decide if you want one or two classes for income and expense. Whatever you find more convenient.
  val b = new Budget
  b + Income(3000, "main", "Corpo. salary Cucumber International")
  b - Expense(100, "investment", "Cucumbers for pickling")
  b + Income(2000, "main", "Corpo. salary Cucumber International")
  b - Expense(10, "investment", "Dill")
  b - Expense(750, "investment", "Barrel")
  b - Expense(5, "consumption", "Beer")

  b + Income(200, "side", "Cucumbers sale")
  b + Income(20, "side", "Barrel rental")

  println(b.total) // total sum
  println(b.expenses) // list of all expenses
  println(b.expenses("investment"))
  println(b.incomes("main")) // print all in category
  println(b.track) // list of amount of money after each transaction 3000, 2900, 5900
  println(b.maxExpense)
  //println(b.trackWith(_.category) )// track with additional info collected by user provided function
}
