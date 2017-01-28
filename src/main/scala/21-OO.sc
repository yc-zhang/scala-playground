class Person(var name: String, val age: Int)

val p = new Person("20", 12)

p.age
p.name

case class ImmutablePerson(name: String, age: Int)

class Dollar(val v: Float) {
  override def toString = "$%.2f".format(v)
}

val dollar = new Dollar(100)

import OO.Address

val shiyuan = new Address("712000")
val zhonghaicheng = new Address("South 3 Road", "Xi'an", "Shannxi", "710056")
