import scala.math.sqrt

object Task2 {
  trait HumanComparator
  {
    def A:Double
   def toHuman():String={
     if(A==0)"Right where you are"
     else if(A<=10.0)"In your Neighbourhood"
     else "Far away"
   }
    def rightWhereYouAre()=toHuman()=="Right where You are"
    def inYourNeighborhood()=toHuman()=="In your Neighbourhood"
    def farAway()=toHuman()=="Far away"
  }
  class Formatted(val A:Double) extends HumanComparator
  class TDistance(a1:(Int,Int), a2:(Int,Int))
  {
    val A=sqrt((a2._1-a1._1)*(a2._1-a1._1)+(a2._2-a1._2)*(a2._2-a1._2))
  }




  def main(args: Array[String]): Unit = {
    val x = new Formatted(0.0)
    println(x.toHuman) // Right where you are

    val y = new Formatted(5.0)
    println(y.toHuman) // In your neighborhood

    val z = new Formatted(25.0)
    println(z.toHuman) // Far away
    println(f"${z.rightWhereYouAre()} ${z.inYourNeighborhood()} ${z.farAway()}") // false false true

    val dist = new TDistance((1, 1), (4, 4)) with HumanComparator
    println(dist.toHuman) // In your neighborhood
  }
}
