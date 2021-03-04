object Task2 {
  def isInside(leftBottomCorner: (Int, Int), rightTopCorner: (Int, Int), point: (Int, Int)): Boolean = point._1 >= leftBottomCorner._1 && point._2 >= leftBottomCorner._2 && point._1 <= rightTopCorner._1 && point._2 <= rightTopCorner._2

  @scala.annotation.tailrec
  def check(leftBottomCorner: (Int, Int), rightTopCorner: (Int, Int), list: List[(Int, Int)], cnt: Int = 0): Int = {
    if (list.isEmpty)
      return cnt
    val newcnt = cnt + (if (isInside(leftBottomCorner, rightTopCorner, list.head)) 1 else 0)
    check(leftBottomCorner, rightTopCorner, list.tail, newcnt)
  }

  def main(args: Array[String]): Unit = {
    val lb = (0, 0)
    val rt = (10, 10)
    val list = List((0, -1), (0, 4), (9, 2), (20, -5))
    println(s"Answer:${check(lb, rt, list)}")

  }
}
