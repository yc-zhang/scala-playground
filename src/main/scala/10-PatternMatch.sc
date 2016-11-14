val bools = Seq(true, false)

for (bool <- bools) {
  bool match {
    case true => println("true")
    case false => println("false")
  }
}

// this will cause the scala.MatchError

//for (bool <- bools) {
//  bool match {
//    case true => println("true")
////    case false => println("false")
//  }
//}

for (x <- Seq(1, 2, 2.7, "one", "two", 'four)) {
  val s = x match {
    case 1 => "Int 1"
    case i:Int => s"Int value $i"
    case d:Double => s"Double Value $d"
    case "one" => "String one"
    case s:String => s"String value $x"
    case unexpected => s"unexpected $unexpected"
  }
  println(s)
}

def check(y: Int): Unit = {
  for {x <- Seq(99, 100, 101)} {
    val str = x match {
      case `y` => "Found Y"
//      case y => "Found Y"
      case i: Int => s"Int $i"
    }
    println(str)
  }
}

check(100)

for (x <- Seq(1, 2, 2.7, "one", "two", 'four)) {
  val s = x match {
    case 1 => "Int 1"
    case _:Int | _:Double => s"Number $x"
    case "one" => "String one"
    case s:String => s"String value $x"
    case unexpected => s"unexpected $unexpected"
  }
  println(s)
}

var nonEmptySeq = Seq(1, 2, 3, 4, 5)
var emptySeq = Seq.empty[Int]
val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)

def seqToString[T](seq: Seq[T]): String = seq match {
  case head +: tail => s"($head +: ${seqToString(tail)})"
  case Nil => "(Nil)"
}

for (seq <- Seq(nonEmptySeq, emptySeq, nonEmptyMap.toSeq)) {
  println(seqToString(seq))
}

val nonEmptyList = List(1, 2, 3, 4, 5)
val emptyList = Nil

def listToString[T](list: List[T]): String = list match {
  case head :: tail => s"($head :: ${listToString(tail)}"
  case Nil => "(Nil)"
}

for (list <- List(nonEmptyList, emptyList)) {
  println(listToString(list))
}

val langs = Seq (
  ("Scala", "Martin", "Odersky"),
  ("Clojure", "Rich", "Hichey"),
  ("Lisp", "John", "McCarthy")
)

for (tuple <- langs) {
  tuple match {
    case ("Scala", _, _) => println("Found Scala")
    case (l, f, n) => println(s"Language $l, Creator ($f $n)")
  }
}

for (i <- Seq(1, 2, 3, 4)) {
  i match {
    case _ if i % 2 == 0 => println(s"Even: $i")
    case _ => println(s"Odd: $i")
  }
}
