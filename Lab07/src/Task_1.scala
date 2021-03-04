

object Task_1 {
  class TripleContainer[+T](container: T, container1: T, container2: T) {
    override def toString = s"$container+$container1+$container2"
    def _1[T](a:T)=new TripleContainer(a,container1,container2)
    def _2[T](a:T)=new TripleContainer(container,a,container2)
    def _3[T](a:T)=new TripleContainer(container,container1,a)
  }

  class SContainer(val str: String) {
    override def toString() = str
  }


  class ItoSContainer(val num: Int) extends SContainer(num.toString) {}
def main(args:Array[String]){
  val tc = new TripleContainer(1,3,5);

  println(tc)





  val sss = new TripleContainer(new SContainer("k1"), new SContainer("k2"), new SContainer("k3"))
  println(sss)
  val sis = new TripleContainer(new SContainer("k1"), new ItoSContainer(67), new SContainer("k3"))
  println(sis)
  val iii = new TripleContainer(new ItoSContainer(88765), new ItoSContainer(67), new ItoSContainer(78))
  println(iii)
  val iis = sis._1(new ItoSContainer(1))
  println(iis)
  val iss = iis._2(new SContainer("One"))
  println(iss)
  val isi = iss._3(new ItoSContainer(3))
  println(isi)

//  Result:
//
//    1 3 5
//
//  k1 k2 k3
//
//  k1 67 k3
//
//  88765 67 78
//
//  1 67 k3
//
//  1 One k3
//
//  1 One 3
}
}
