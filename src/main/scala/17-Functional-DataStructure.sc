val list1 = List("Programming", "Scala")

val list2 = "People" :: "should" :: "read" :: list1

// null is not allowed here
val list3 = "Please" :: "read" :: "the fucking" :: "menu" :: Nil

val list4 = "People" :: "should" :: "read" :: Nil

val list5 = list4 ++ list1

val seq1 = Seq("Programming", "Scala")

val seq2 = seq1 :+ "Rocks!"

val map1 = Map("Yuchen" -> "Zhang", "Rundong" -> "Lian")

map1.foreach(name => println(name._1))

list5.map(a => a.toLowerCase())

val map2 = Map("Yuchen" -> Seq("Java", "Ruby", "C#"), "Rundong" -> Seq("Java", "Python", "JS"))

map2.flatMap(f => f._2.toList)

map2.filter(f => f._1 == "Yuchen")

map2.flatMap(f => f._2.toList).toSet.reduce(_ + _)



