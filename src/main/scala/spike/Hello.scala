package spike

object App {

  def main(args: Array[String]): Unit = {
    MMP.hello
    MMP.hello
  }
}

object MMP {
  val test: String = getTestText

  def hello: Unit = {
    println(test)
  }

  def getTestText: String = {
    println("running get test text")
    "i'm here"
  }
}

