import akka._
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
object SMTH {
  case class Calculated(value:Int)
  case class Job(word:String)
  case class Start(list:List[ActorRef])
  class Manager(var Joblist: List[String]) extends Actor {
    var sum=0
    var ended=0
    var WorkerList=List[ActorRef]()
    def receive = {
      case Start(list)=>{
        WorkerList=list
        for(x<-WorkerList){
          if(Joblist.nonEmpty){
            x!Job(Joblist.head)
            Joblist=Joblist.tail
          }

        }
      }
      case Calculated(value)=>{
        sum+=value
        if(Joblist.isEmpty){
          ended+=1
          if(ended==WorkerList.size){
            println("The final value is "+sum)
          }

        }
        else{
          sender()!Job(Joblist.head)
          Joblist=Joblist.tail
        }


      }
      case _ => println("Something went wrong in Manager")
    }
  }
  object Manager{
    def apply(Joblist: List[String]): Manager = new Manager(Joblist)
  }
  class Worker(Boss:ActorRef) extends Actor{
    def checksum(word:String):Int={
    word.toList.map(_.toInt).sum
    }
    def receive={
      case Job(word)=>{
        Boss!Calculated(checksum(word))
      }
      case _ => println("Something went wrong in Worker")
    }
  }
  object Worker{
    def apply(Boss: ActorRef): Worker = new Worker(Boss)
  }
  def main(args: Array[String]): Unit = {
    val system=ActorSystem("Default")
    val list:List[String]="Hello Darkness my old Friend".split(" ").toList
    val manager=system.actorOf(Props(classOf[Manager],list))
    val FirstWorker=system.actorOf(Props(classOf[Worker],manager))
    val SecondWorker=system.actorOf(Props(classOf[Worker],manager))
    val ThirdWorker=system.actorOf(Props(classOf[Worker],manager))
    val WorkerList=List(FirstWorker,SecondWorker,ThirdWorker)
    manager!Start(WorkerList)

  }
}
