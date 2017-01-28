import scala.util.{Failure, Success, Try}

val results: Seq[Option[Int]] = Vector(Some(10), None, Some(20))

val results2 = for {
  Some(i) <- results
} yield i * 2

val results2b = for {
  Some(i) <- results.withFilter {
    case Some(i) => i > 10
    case None => false
  }
} yield i

val results2c = for {
  Some(i) <- results.withFilter(isAccept)
} yield i

def isAccept(s: Option[Int]): Boolean = {
  s match {
    case Some(i) => i > 10
    case None => false
  }
}

val l: Either[String, Int] = Left("boo")

val r: Either[String, Int] = Right(12)

type Or[A, B] = Either[A, B]

val lr: String Or Int = Left("Boo")

def addInts(s1: String, s2: String): Either[NumberFormatException, Int] = {
  try {
    Right(s1.toInt + s2.toInt)
  } catch {
    case e: NumberFormatException => Left(e)
  }
}

def addInts2(s1: String, s2: String): Try[Int] = {
  try {
    Success(s1.toInt + s2.toInt)
  } catch {
    case e: NumberFormatException => Failure(e)
  }
}


for {
  i <- 1 to 3
  j <- 1 to i
} println(s"$i + $j = ${addInts(i.toString, j.toString)}")

addInts("0", "x")



