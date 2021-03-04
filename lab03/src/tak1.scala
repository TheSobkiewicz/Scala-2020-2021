object tak1 {
  def partition(list: List[Int], predicate: Int => Boolean): List[List[Int]] = {
    var tr = List[Int]()
    var fal = List[Int]()

    @scala.annotation.tailrec
    def part(list: List[Int], predicate: Int => Boolean) {
      if (list.isEmpty) return
      if (predicate(list.head)) tr = tr :+ list.head
      else fal = fal :+ list.head
      part(list.tail, predicate)
    }

    part(list, predicate)
    List(tr, fal)
  }

  def main(args: Array[String]): Unit = {
    println(partition(list = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), predicate = _ % 2 == 0))
  }

}
