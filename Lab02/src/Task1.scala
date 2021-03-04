object Task1 {
  def sum(a: Int, b: Int) = a + b

  def dif(a: Int, b: Int) = a - b

  def div(a: Int, b: Int) = a / b

  def mul(a: Int, b: Int) = a * b
  val x: Map[Char, (Int, Int) => Int] =
    Map('+' -> sum, '-' -> dif, '*' -> mul, '/' -> div)
  def solveEquation(eq:String):Int={
    val neq=eq.split(' ')
    x(neq(1)(0))(neq(0).toInt,neq(2).toInt)
  }
  def main(args: Array[String]): Unit = {
    val eq = "1 * 2"
println(s"Answer: ${solveEquation(eq)}")

  }
}