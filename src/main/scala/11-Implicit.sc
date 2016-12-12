implicit val currentTaxRate = 0.08F

def calcTax(amount: Float)(implicit rate: Float): Float = amount * rate

calcTax(1000F)

calcTax(1000F)(0.1F)

def curry(amount: Int)(price: Float): Float = amount * price

val t = curry(1)(2)
