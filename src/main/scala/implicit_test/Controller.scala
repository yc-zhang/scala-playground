package implicit_test

object Controller extends Controller(Registration.active) {
  def activateAction(): String = activeUser("yuchen")
}

class Controller(active: String => String) {
  def activeUser(name: String): String = {
    implicit val transactionId: TransactionId = "test-trans-id"
    println(active(name))
    "finish"
  }
}
