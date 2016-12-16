import com.sun.glass.ui.Window.Level

case class Address(street: String, city: String)
case class Person(name: String, address: Address)

trait ToJSON {
  def toJSON(level: Int = 0): String

  val INDENTATION = " "

  def indentation(level: Int = 0): (String, String) =
    (INDENTATION * level, INDENTATION * (level + 1))
}

implicit class AddressToJSON(address: Address) extends ToJSON {
  override def toJSON(level: Int): String = {
    val (outdent, indent) = indentation(level)
    s"""{
       |${indent}"street": "${address.street}",
       |${indent}"city": "${address.city}"
       |$outdent}""".stripMargin
  }
}

implicit class PersonToJSON(person: Person) extends ToJSON {
  override def toJSON(level: Int): String = {
    val (outdent, indent) = indentation(level)
    s"""{
       |${indent}"name": "${person.name}",
       |${indent}"address": ${person.address.toJSON(level + 1)}
       |$outdent}""".stripMargin
  }
}

class NewPersonToJSON(person: Person) extends ToJSON {
  override def toJSON(level: Int): String = {
    val (outdent, indent) = indentation(level)
    s"""{
        |${indent}"name": "${person.name}",
        |${indent}"address": ${person.address.toJSON(level + 1)}
        |$outdent}""".stripMargin
  }
}

val address = new Address("The third ring road", "Xi'an")
val person = new Person("Wenting", address)

person.toJSON()
