val states = List("Shaanxi", "Shanxi", "Sichuan", "Chongqing")

for {
  s <- states
} println(s)

for {
  s <- states
} yield s.toUpperCase

states map (_.toUpperCase)

for {
  s <- states
  c <- s
} yield s"$c-${c.toUpper}"

for {
  s <- states
  c <- s
  if c.isLower
} yield s"$c-${c.toUpper}"

for {
  s <- states
  c <- s
  if c.isLower
  c2 = s"$c-${c.toUpper}"
} yield c2

val map = Map("one" -> 1, "two" -> 2)

val list = for {
  (k, v) <- map
  i = v + 10
} yield (k, i)
