package main.implicits {
  package scaladb {
    object implicits {
      import javadb.JRow
      implicit class SRow(jRow: JRow) {
        def get[T](columnName: String)(implicit toT: (JRow, String) => T): T = toT(jRow, columnName)
      }
      implicit val jrowToInt: (JRow, String) => Int = (jRow: JRow, columnName: String) => jRow.getInt(columnName)
      implicit val jrowToDouble: (JRow, String) => Double = (jRow: JRow, columnName: String) => jRow.getDouble(columnName)
      implicit val jrowToString: (JRow, String) => String = (jRow: JRow, columnName: String) => jRow.getString(columnName)
    }

    object DB {
      import implicits._

      def main(args: Array[String]) = {

        val row = javadb.JRow("one" -> 1, "two"-> 2.2d, "three" -> "THREE!")

        println(s"row1 = ${row.getInt("one")}")
        println(s"row2 = ${row.getDouble("two")}")
        println(s"row3 = ${row.getString("three")}")

        println("scala db: ")
        println(s"row1 = ${row.get[Int]("one")}")
        println(s"row2 = ${row.get[Double]("two")}")
        println(s"row3 = ${row.get[String]("three")}")
      }
    }
  }

}
