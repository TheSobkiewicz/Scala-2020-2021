  object Task1 {
    class SDistance(val Kilometers:Int, val Meters:Int, val Centimeters:Int)
    {
      override def toString =s"Kilometers :$Kilometers Meters: $Meters Centimeters: $Centimeters\n"
      def sum():Double=Kilometers*1000+Meters+Centimeters*0.01
      def == (ant:SDistance):Boolean=sum()==ant.sum()
      def !=(ant:SDistance) = ! ==(ant)
      def<(ant:SDistance):Boolean=sum()<ant.sum()
      def>(ant:SDistance):Boolean=sum()>ant.sum()
      def add(int: Int, value:SDistance.Value): SDistance =
      {
        if (value==SDistance.Kilometers) SDistance(Kilometers+int,Meters,Centimeters)
        else if (value==SDistance.Meters)  SDistance(Kilometers,Meters+int,Centimeters)
        else  SDistance(Kilometers,Meters,Centimeters+int)
      }

    }
    object SDistance extends Enumeration
    {
      def apply(km:Int,m:Int,cm:Int) =new SDistance(km ,m,cm)
      val Kilometers,Meters, Centimeters =Value
    }
      def main(args: Array[String]): Unit = {
        val firstDistance = SDistance(2, 2, 2)
        val secondDistance = SDistance(2, 2, 2)
        val thirdDistance = SDistance(2, 2, 3)
        val fourthDistance = SDistance(2, 2, 1)

        println("Distances:")
        println(firstDistance) // Kilometers: 2, Meters : 2, Centimeters: 2
        println(secondDistance) // Kilometers: 2, Meters : 2, Centimeters: 2
        println(thirdDistance) // Kilometers: 2, Meters : 2, Centimeters: 3
        println(fourthDistance) // Kilometers: 2, Meters : 2, Centimeters: 1

        println("2:")
        println(firstDistance == secondDistance) // true
        println(firstDistance != secondDistance) // false
        println(firstDistance > secondDistance) // false
        println(firstDistance < secondDistance) // false

        println("3:")
        println(firstDistance == thirdDistance) // false
        println(firstDistance != thirdDistance) // true
        println(firstDistance > thirdDistance) // false
        println(firstDistance < thirdDistance) // true

        println("4:")
        println(firstDistance == fourthDistance) // false
        println(firstDistance != fourthDistance) // true
        println(firstDistance > fourthDistance) // true
        println(firstDistance < fourthDistance) // false
        val fifthDistance = firstDistance.add(2, SDistance.Meters) // here you have to be able to add also meters or centimeters
        println(fifthDistance) // Kilometers: 4, Meters: 2, Centimeters: 2
      }
}
