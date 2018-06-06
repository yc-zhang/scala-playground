package implicit_test

object Registration {
  def active(name: String)(implicit transactionId: TransactionId): String = {
    "active, " + transactionId
  }
}
