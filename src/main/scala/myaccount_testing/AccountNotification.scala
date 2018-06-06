package myaccount_testing

import myaccount_testing.common.Mailer

trait AccountNotification {
  def notifyPasswordReset(email: String)(implicit tras: String): String
}

trait AccountEmailNotification extends AccountNotification with Mailer {
  override def notifyPasswordReset(email: String)(implicit tras: String): String = {
    send(email)
  }
}
