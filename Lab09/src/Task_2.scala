import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._
import scala.util._
import math.{ min, max }

object Task2 {
  def mapReduce(s: List[Int]): Future[(Int, Int)] = {
    val splitted = s.grouped(s.length / 10).toSeq // This splits list of Numbers into 10 sequences of numbers - it's first point from plan
//  println(splitted)
//    println((splitted.map(i=>i.foldLeft(i.head,i.head){case ((amin,amax),b) =>(min(amin,b),max(amax,b))})))
    val f:Future[Seq[(Int,Int)]]={
      Future((splitted.map(i=>i.foldLeft(i.head,i.head){case ((amin,amax),b) =>(min(amin,b),max(amax,b))})))
    }
      f.map(a=>a.reduce((a,b)=>(min(a._1,b._1),max(a._2,b._2))))

    // TODO: Create sequence of features for each chunk, where each future will find tuple of (min, max) inside single chunk (map).

    // TODO: Combine results of all chunks to single tuple (min, max) - (reduce).
  }

  def main(args: Array[String]): Unit = {
    val rNumbers = List.fill(100)(Random.nextInt())

     println((rNumbers.min, rNumbers.max)) // You can check if the result is correct
    val r = mapReduce(rNumbers)

    r onComplete {
      case Success(res) => println(res) // Should be tuple: (MinNumber, MaxNumber)
    }

    Await.ready(r, Duration(10, SECONDS))
  }
}