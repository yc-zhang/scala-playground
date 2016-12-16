package implicits.payroll

// trait as tag
sealed trait PreTaxDeductions
sealed trait PostTaxDeductions
sealed trait Final

case class Employee(name: String,
                    annualSalary: Float,
                    taxRate: Float,
                    insurancePremiumsPrePayPeriod: Float,
                    _401kDeductionRate: Float,
                    postTaxDeductions: Float)

case class Pay[Step](employee: Employee, netPay: Float)

object PayRoll {
  def start(employee: Employee): Pay[PreTaxDeductions] =
    Pay[PreTaxDeductions](employee, employee.annualSalary / 26.0F)

  def minusInsurance(pay: Pay[PreTaxDeductions]): Pay[PreTaxDeductions] = {
    val newNet = pay.netPay - pay.employee.insurancePremiumsPrePayPeriod
    pay copy(netPay = newNet)
  }

  def minus401K(pay: Pay[PreTaxDeductions]): Pay[PreTaxDeductions] = {
    val newNet = pay.netPay - pay.employee._401kDeductionRate * pay.netPay
    pay copy(netPay = newNet)
  }

  def minusTax(pay: Pay[PreTaxDeductions]): Pay[PostTaxDeductions] = {
    val newNet = pay.netPay - pay.employee.taxRate * pay.netPay
    pay copy(netPay = newNet)
  }

  def minusFinalDeductions(pay: Pay[PostTaxDeductions]): Pay[Final] = {
    val newNet = pay.netPay - pay.employee.postTaxDeductions
    pay copy(netPay = newNet)
  }
}

object Pipeline {
  implicit class toPiped[V](value: V) {
    def |>[R](f: V => R) = f(value)
  }
}

object CalculatePayRoll {
  def main(args: Array[String]) = {
    way
    way2
  }

  def way: Unit = {
    val e = Employee("Buck Trends", 100000F, 0.25F, 200F, 0.1F, 0.05F)
    val pay1 = PayRoll.start(e)
    val pay2 = PayRoll minus401K pay1
    val pay3 = PayRoll minusInsurance pay2
    val pay4 = PayRoll minusTax pay3
    val pay = PayRoll minusFinalDeductions pay4
    val twoWeekGross = e.annualSalary / 26F
    val twoWeekNet = pay.netPay
    val percent = (twoWeekNet / twoWeekGross) * 100
    println(s"For ${e.name}, the gross vs. net pay evert 2 weeks is:")
    println(f"  $$$twoWeekGross%.2f vs. $$$twoWeekNet%.2f or $percent%.1f%%")
  }

  def way2: Unit = {
    import Pipeline._
    import PayRoll._

    val e = Employee("Buck Trends", 100000F, 0.25F, 200F, 0.1F, 0.05F)

    val pay = start(e) |> minus401K |> minusInsurance |> minusTax |> minusFinalDeductions

    val twoWeekGross = e.annualSalary / 26F
    val twoWeekNet = pay.netPay
    val percent = (twoWeekNet / twoWeekGross) * 100
    println(s"For ${e.name}, the gross vs. net pay evert 2 weeks is:")
    println(f"  $$$twoWeekGross%.2f vs. $$$twoWeekNet%.2f or $percent%.1f%%")
  }
}
