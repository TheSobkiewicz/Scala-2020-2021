import java.awt.Dimension

import akka.actor.{Actor, ActorRef, ActorSelection, ActorSystem, Props}

import scala.swing._
import scala.util.Random

case class BallPosition(x: Int, y: Int)
case class DontMove()
case class MoveY(delta:Int)
case class MoveX(delta:Int)
case class BallChanger(x:Int, y:Int, nball:Char)
case class ChangeBall()

class Ball extends Actor {
  private val areaMessenger: ActorSelection = context.actorSelection("../Area")
  var allowMoving = true
  var x, y: Int = 0

  /**
   * This methods changes position of "ball" on the "x" axis. Remember to disallow having x lower than 0
   * @param delta   Move delta
   */
  private def moveX(delta: Int): Unit = {
    if(allowMoving){
      x=x+delta
      if(x<0)x=0
    }
    areaMessenger ! BallPosition(x,y)
  }

  /**
   * This methods changes position of "ball" on the "y" axis. Remember to disallow having x lower than 0
   * @param delta Move delta
   */
  private def moveY(delta: Int): Unit ={
    if(allowMoving){
      y=y+delta
      if(y<0)y=0
    }
    areaMessenger ! BallPosition(x,y)
  }
  private  def changeBall(){
    val nball:Char= Random.alphanumeric.filter(_.isLetter).head
    areaMessenger ! BallChanger(x,y,nball)
  }

  /**
   * Overrides receive method from actor class. Should be pattern matching over possible messages.
   * @return
   */
  override def receive: Receive = {
    case DontMove => allowMoving= !allowMoving
    case MoveY(delta)=> moveY(delta)
    case MoveX(delta)=>moveX(delta)
    case ChangeBall=> changeBall()
  }
}

class Area(val textPane: TextPane) extends Actor {
  /**
   * Overrides receive method from actor class. Moves "ball" accordingly to message
   * @return
   */
  var ball:Char='x'
  override def receive: Receive = {

    case BallPosition(x,y)=>{
      textPane.text= "\n"*y + "\t"*x + ball
    }
    case BallChanger(x,y,nball)=>{
   ball=nball
      textPane.text= "\n"*y + "\t"*x + ball
    }

    case _ =>{
      textPane.text="ERROR OCCURED"
    }

  }
}

object Main extends SimpleSwingApplication {
  val actorSystem: ActorSystem = ActorSystem("Default")

  val textPane = new TextPane
  textPane.text = "x"
  textPane.editable = false
  textPane.minimumSize = new Dimension(600, 300)

  val area: ActorRef = actorSystem.actorOf(Props(new Area(textPane)), name = "Area")
  val ballMessenger: ActorRef = actorSystem.actorOf(Props(new Ball), name = "Ball")

  val leftButton = new Button(Action("a") {
    ballMessenger ! MoveX(-1)
  })
  val rightButton = new Button(Action("d") {
    ballMessenger ! MoveX(1)
  })
  val upButton = new Button(Action("w") {
    ballMessenger ! MoveY(-1)
  })
  val downButton = new Button(Action("s") {
    ballMessenger ! MoveY(1)
  })
  val toggleMove = new Button(Action("toggle move") {
    ballMessenger ! DontMove
  })

  def top: MainFrame = new MainFrame {
    title = "Pimp my ball"
    contents = new BoxPanel(Orientation.Vertical) {
      contents += textPane
      contents += new BoxPanel(Orientation.Vertical) {
        override def size: Dimension = new Dimension(600, 100)

        contents += new BoxPanel(Orientation.Horizontal) {
          contents += new Button(Action(" change ball ") {
            ballMessenger ! ChangeBall
          })
          contents += upButton
          contents += toggleMove
        }
        contents += new BoxPanel(Orientation.Horizontal) {
          contents += leftButton
          contents += downButton
          contents += rightButton
        }

      }
    }
    size = new Dimension(600, 400)
  }
}
