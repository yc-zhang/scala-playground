import java.util.concurrent.atomic.LongAccumulator

import scala.annotation.tailrec

def m1 (multiplier: Int => Int): Int = {
  (1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)
}

def m2: Int => Int = {
  val factor = 2
  (i: Int) => i * factor
}

m1(m2)

object Multiplier {
  val factor = 2
  def multiplier(i: Int) = i * factor
}

m1(Multiplier.multiplier)

def factorial(i: BigInt): BigInt = {
  @tailrec
  def fact(i: BigInt, accumulator: BigInt): BigInt =
    if (i == 1) accumulator
    else fact(i - 1, i * accumulator)

  fact(i, 1)
}

for (i <- 1 to 10)
  println(s"$i: ${factorial(i)}")

def cat1(s1: String)(s2: String) = s1 + s2

val hello = cat1("Hello ")_

hello("world")
