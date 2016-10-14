class Upper {
  def upper(strings: String*): Seq[String] = strings.map(s => s.toUpperCase)
}

val up = new Upper
println(up.upper("Hello", "world"))


def equalSign(s: String) =
  println("equalSign: " + s)

equalSign("hi")

def equalSign2(s: String) = {
  println("equalSign2: " + s)
}

var stockPrice: Double = 100.0

stockPrice = 200.0

class Person(val name: String, var age: Int)

var me = new Person("Yuchen", 20)

//me.name = "Zhang" // you can't assign value to val
me.age = 33

println(me.name + " " + me.age)

val lele = new Person("Lele", 30)

lele.age = 33 // it's permitted
// lele = new Person("Another Lele", 30) //it's not
