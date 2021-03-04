object ColorShapes {

  trait Color {
    def color: (Double, Double, Double)

    var r = color._1
    var g = color._2
    var b = color._3

    def decreaseGreenBy(a1: Double) {
      g = g - a1
    }

    def decreaseRedBy(a1: Double) {
      r = r - a1
    }

    def decreaseBlueBy(a1: Double) {
      b = b - a1
    }

    def increaseRedBy(a1: Double) {
      r = r + a1
    }

    def increaseBlueBy(a1: Double) {
      b = b + a1
    }

    def increaseGreenBy(a1: Double) {
      g = g + a1
    }

    def setColor(a: (Int, Int, Int)) {
      r = a._1
      g = a._2
      b = a._3
    }
  }

  trait Shape {
    val center: (Int, Int)
    val size: Int
  }

  class Triangle(val center: (Int, Int), val rotation: Int, val size: Int) extends Shape {
    override def toString(): String = s"Triangle of side size of $size rotation : $rotation at $center"
  }

  object Triangle {
    def apply(center: (Int, Int), rotation: Int, size: Int): Triangle = new Triangle(center, rotation, size)
  }

  class Square(val center: (Int, Int), val size: Int) extends Shape {
    override def toString(): String = s"Square of side size of $size  at $center"
  }

  object Square {
    def apply(center: (Int, Int), size: Int): Square = new Square(center, size)
  }

  class ColorTriangle(center: (Int, Int), rotation: Int, size: Int, val color: (Double, Double, Double)) extends Triangle(center, rotation, size) with Color {
    override def toString(): String = s"Triangle of side size of $size rotation : $rotation at $center r:$r g:$g b:$b"
  }

  object ColorTriangle {
    def apply(center: (Int, Int), rotation: Int, size: Int, color: (Double, Double, Double)): ColorTriangle = new ColorTriangle(center, rotation, size, color)
  }

  class ColorSquare(center: (Int, Int), size: Int, val color: (Double, Double, Double)) extends Square(center, size) with Color {
    override def toString(): String = s"Square of side size of $size at $center r:$r g:$g b:$b"
  }

  object ColorSquare {
    def apply(center: (Int, Int), size: Int, color: (Double, Double, Double)) = new ColorSquare(center, size, color)
  }

  def main(args: Array[String]) = {
    val a = ColorTriangle(center = (0, 0), rotation = 30, size = 25, color = (20, 20, 120))

    a.setColor(100, 0, 70)

    a.increaseRedBy(2.0)

    val b = ColorSquare(center = (3, 3), size = 12, color = (20, 20, 120))

    b.setColor(10, 255, 0)

    b.decreaseGreenBy(0.3)


    val shapes = List[Shape](Triangle((0, 0), 90, 50), a, Square((3, -3), 10), b)

    println(shapes.mkString("\n"))

    /* expected result

  Triangle of side size 50 rotation 90 At (0,0)

  Triangle of side size 25 rotation 30 At (0,0) with color r:255 g: 0 b: 70

  Square of size 10 At (3,-3)

  Square of size 12 At (3,3) with color r:10 g: 178 b: 0

     */

  }

}
