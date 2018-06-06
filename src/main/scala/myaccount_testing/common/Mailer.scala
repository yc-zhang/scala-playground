package myaccount_testing.common

trait Mailer {
  def send(email: String)(implicit map: String): String
}

trait PlayMailer extends Mailer {
  override def send(email: String)(implicit transactionId: String): String = {
    s"send to $email, $transactionId"
  }
}

