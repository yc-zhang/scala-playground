package main.implicits {
  package database_api {

    case class InvalidColumnName(name: String) extends RuntimeException(s"Invalid column name $name")

    trait Row {
      def getInt(columnName: String): Int

      def getDouble(columnName: String): Double

      def getString(columnName: String): String
    }

  }

  package javadb {

    import database_api._

    case class JRow(representation: Map[String, Any]) extends Row {
      private def get(columnName: String): Any = {
        representation.getOrElse(columnName, throw InvalidColumnName(columnName))
      }

      override def getInt(columnName: String): Int = get(columnName).asInstanceOf[Int]

      override def getDouble(columnName: String): Double = get(columnName).asInstanceOf[Double]

      override def getString(columnName: String): String = get(columnName).asInstanceOf[String]
    }

    object JRow {
      def apply(pairs: (String, Any)*): JRow = new JRow(Map(pairs: _*))
    }

  }
}