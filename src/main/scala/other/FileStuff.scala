package other

import java.nio.charset.Charset
import java.nio.file.Paths

import scala.io.Source

object FileStuff {
  def main(args: Array[String]): Unit = {
    val files = Paths.get("/Users/yczhang/Downloads/atlas_thin/bounded_areas/").toFile.listFiles()
    for (file <- files) {
      Charset.defaultCharset()
      val source = Source.fromFile(file, "UTF-8")
      try {
        source.mkString
      } catch {
        case _: Throwable => println(file.toString)
      } finally {
        source.close()
      }
    }
  }
}
