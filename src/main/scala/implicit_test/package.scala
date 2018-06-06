package object implicit_test {
  type TransactionId = String
  implicit val transactionId: TransactionId = "-"
}
