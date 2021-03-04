
object task2 {

  class ToDoItem(name: String, date: String) {
    var IsDone: Boolean = false

    override def toString(): String = {
      s"$name - till $date | DONE: $IsDone\n"
    }
  }

  class ToDoList() {
    var x: List[ToDoItem] = Nil

    def +(toDoItem: ToDoItem): Unit = {
      x = x :+ toDoItem
    }

    def markAsDone(i: Int): Unit = {
      x(i).IsDone = true
    }

    def del() {
      x = x.filter(!_.IsDone)
    }

    override def toString(): String = {
      var sum: String = ""
      x.foreach(sum += _.toString())
      sum
    }

  }

  def main(args: Array[String]) = {
    val toDoList = new ToDoList()

    toDoList + new ToDoItem("Clean carpet", "2020-11-01")
    toDoList + new ToDoItem("Wash dishes", "2020-11-02")
    toDoList + new ToDoItem("Learn Scala and be professional with it", "2020-10-28")

    println(toDoList)

    // Prints list of all three ToDoItems nicely formatted, each in separate line

    toDoList.markAsDone(0)
    toDoList.del()

    println(toDoList)

    // Prints list of two ToDoItems without the first one
  }

}
