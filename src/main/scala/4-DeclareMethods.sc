case class Point(x: Double = 0.0, y: Double = 0.0) {
  def shift(deltax: Double = 0.0, deltay: Double = 0.0) =
    copy(x + deltax, y + deltay)
}

val p1 = new Point(3.3, 4.4)

val p2 = p1.copy(x = 3.3)

val p3 = p1.copy(y = 1.1)
