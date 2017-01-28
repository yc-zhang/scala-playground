package OO

case class Address(street: String, city: String, state: String, zip: String) {
  def this(zip: String) = this("[unknown]", Address.zipToCity(zip), Address.zipToState(zip), zip)
}

object Address {
  def zipToCity(zip: String) = "Anytown"
  def zipToState(zip: String) = "CA"
}
