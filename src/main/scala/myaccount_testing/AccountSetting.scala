package myaccount_testing

import myaccount_testing.common.PlayMailer

trait AccountSetting extends AccountNotification {
  def doSomething(): String = {
    implicit val trea: String = "aaa"
    notifyPasswordReset("aaa")
  }
}

object AccountSettings extends AccountSetting with AccountEmailNotification with PlayMailer {
}