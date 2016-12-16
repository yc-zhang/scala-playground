implicit val currentTaxRate = 0.08F

def calcTax(amount: Float)(implicit rate: Float): Float = amount * rate

calcTax(1000F)

calcTax(1000F)(0.1F)
//
//def curry(amount: Int)(price: Float): Float = amount * price
//
//val t = curry(1)(2)

object SimpleStateSalesTax {
  implicit val rate: Float = 0.05F
}

case class ComplicatedSalesTaxData (baseRate: Float, isTaxHoliday: Boolean, storeId: Int)

object ComplicatedSalesTax {
  private def extraTaxRateForStore(id: Int): Float = {
    0.0F
  }

  implicit def rate(implicit cstd: ComplicatedSalesTaxData): Float = {
    if (cstd.isTaxHoliday) 0.0F
    else cstd.baseRate + extraTaxRateForStore(cstd.storeId)
  }
}

val amount = 100F
println(s"Tax on $amount = ${calcTax(amount)}")

{
//  import ComplicatedSalesTax.rate
  implicit val myStore = ComplicatedSalesTaxData(0.6F, false, 1010)

  val anotherAmount = 100F
  calcTax(anotherAmount)
}
