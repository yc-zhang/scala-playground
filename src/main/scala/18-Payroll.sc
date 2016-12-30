case class Employee(
   name: String,
   title: String,
   annualSalary: Double,
   taxRate: Double,
   insurancePremiumsPerWeek: Double)

val employees = List(
  Employee("Buck Trends", "CEO", 200000, 0.25, 100.0),
  Employee("Cindy Banks", "CFO", 170000, 0.22, 120.0),
  Employee("Yuchen Zhang", "DEV", 130000, 0.20, 120.0)
)

val netPay = employees map {
  employee => {
    val net = (1.0 - employee.taxRate) * (employee.annualSalary / 52.0) - employee.insurancePremiumsPerWeek
    (employee, net)
  }
}

println("** Paychecks **:")

netPay foreach {
  case (e, net) => println(f"${e.name + ':'}%-16s $net%10.2f")
}
