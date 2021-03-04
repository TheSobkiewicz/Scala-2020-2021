  object task_2 {
    def main(args: Array[String]): Unit = {
      val subjects = List(
        ("Matematyka dyskretna", 1, 1, 4, false),

        ("Algorytmy i struktury danych", 1, 2, 6, true),

          ("Teoria obwodów i sygnałów", 1, 3, 3, false),

        ("Układy elektroniczne", 1, 4, 5, true),

        ("Bazy danych 1", 1, 5, 5, false),

        ("Inżynieria oprogramowania", 1, 6, 5, true),

        ("Komputeryzacja pomiarów", 1, 7, 3, false),

        ("Modelowanie procesrów fizycznych", 2, 1, 5, true),

        ("Techniki mikroprocesorowe", 2, 2, 5, false),

        ("Programowanie zespołowe", 2, 3, 5, false)
      )
  println("Bachalor: "+subjects.filter(_._2==1))
      println("Master: "+subjects.filter(_._2==2))
      println("Ects>5 and exam: " +subjects.filter(_._4>5).filter(_._5))
      def f1(a:(String, Int,Int,Int,Boolean))=a._1+" "+a._4
      println("Subjects with ECTS: "+ subjects.map(a =>(a._1,a._4)))
      println("Subject by ECTS: "+subjects.groupBy(subjects=>subjects._4))
      println("Sum of bachalor: " +subjects.filter(_._2==1).map(_._4).sum)
      println("Sum of Master: " +subjects.filter(_._2==2).filter(!_._5).map(_._4).sum)
      println("Bachalors: " +subjects.filter(_._2==1).sortBy(_._1))

    }
  }
