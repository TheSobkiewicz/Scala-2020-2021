object Task2 {
  class EquationSolver() {
    var result: Double = 0

    override def toString(): String = s"Result: $result"

    def <<(a: Any): EquationSolver = {
      a match {
        case Addition(a: Double) => {
          println(s"Adding $a")
          result = result + a
          this
        }
        case Subtraction(a: Double) => {
          println(s"Subtraction $a")
          result = result - a
          this
        }
        case Division(a: Double) if a != 0 => {
          println(s"Subtraction $a")
          result = result / a
          this
        }
        case Multiplication(a: Double) => {
          println(s"Multiplication $a")
          result = result * a
          this
        }
        case _ => {
          println("Unknown / not allowed operation")
          this
        }

      }

    }

  }

  case class Addition(value: Double)

  case class Subtraction(value: Double)

  case class Division(value: Double)

  case class Multiplication(value: Double)

  def main(args: Array[String]) = {

    val s = new EquationSolver()
    println(s)
    s << Addition(1) << Subtraction(1)
    println(s)
    s << Division(0)
    println(s)
    s << Addition(1) << Multiplication(5)
    println(s)
    s << Division(2)
    println(s)
    s << List[Int](1)
    println(s)
  }


}
