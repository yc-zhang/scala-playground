val f1: (Int, String) => String = (i, s) => i + s
val f2: Function2[Int, String, String] = (i, s) => i + s

f1.apply(22, "321")
f2.apply(1, "2")

val t1: (Int, String) = (1, "two")
val t2: Tuple2[Int, String] = (1, "two")

val t = ("Hello", 1, 2.3)
println("Print the whole tuple: " + t)
println("Print the first  item: " + t._1)
println("Print the second item: " + t._2)
println("Print the third  item: " + t._3)

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska" -> "Juneau",
  "Wyoming" -> "Cheyenne"
)

stateCapitals.get("Alabama").get
stateCapitals.get("Alaska").getOrElse("NotFound")

stateCapitals.getOrElse("Alaska", "NotFound")
stateCapitals.getOrElse("Unknown", "NotFound")
