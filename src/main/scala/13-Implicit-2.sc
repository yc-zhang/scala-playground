val l1 = List(1, 2, 3)

//l1.toMap
// def toMap[T, U](implicit ev: <:<[A, (T, U)]): immutable.Map[T, U]
// -> def toMap[T, U](implicit ev: A <:< (T, U)): immutable.Map[T, U]

val l2 = List("one" -> 1, "two" -> 2, "three" -> 3)
l2.toMap


class Good1 {
  def m(i: Int)(implicit s: String, d: Double) = "boo"
}

class Good2 {
  def m(implicit s: String, d: Double) = "boo"
}
