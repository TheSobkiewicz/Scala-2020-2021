object URL {
  def unapplySeq(str: String): Option[Seq[String]] = {
    if(str.matches( "((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[-;:&=\\+\\$,\\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\\+\\$,\\w]+@)[A-Za-z0-9.-]+)((?:\\/[\\+~%\\/.\\w-_]*)?\\??(?:[-\\+=&;%@.\\w_]*)#?(?:[\\w]*))?)")) {
      Some(str.split("://|[:/?]"))
    }
    else None
  }
}
object Task1 {
  def urlChecker(url: String): Unit = {

    url match {

      case URL(protocol, host, path, query) => println(s"protocol=$protocol, host=$host, path=$path, query=$query")

      case _ => println("Wrong URL")

    }

  }
  def main(args: Array[String])=
  {
    urlChecker("http://fis.agh.edu.pl/dziekanat?test=true&asdf=false") // protocol=http, host=fis.agh.edu.pl, path=dziekanat, query=Map(test -> true, asdf -> false)

    urlChecker("me no URL :(")



  }
}
