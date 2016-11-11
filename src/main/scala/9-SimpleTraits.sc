class ServiceImportante(name: String) {
  def work(i: Int) :Int = {
    println(s"I'm working $i")
    i + 1
  }
}

val service1 = new ServiceImportante("Hi")
for (i <- 1 to 3)
  service1.work(i)

(1 to 3).foreach {
  service1.work
}

(1 to 3).foreach (i => service1.work(i))

trait Logging {
  def info    (message: String): Unit
  def warning (message: String): Unit
  def error   (message: String): Unit
}

trait StdoutLogging extends Logging {
  override def info(message: String): Unit = println("info: ", message)

  override def warning(message: String): Unit = println("warning:", message)

  override def error(message: String): Unit = println("error:", message)
}

val service2 = new ServiceImportante("Dos") with StdoutLogging {
  override def work(i: Int): Int = {
    info(s"Start work with $i")
    val result = super.work(i)
    info(s"End work $result")
    result
  }
}

(1 to 3).foreach (i => service2.work(i))

class LogServiceImportante(name: String) extends ServiceImportante(name: String) with StdoutLogging {
  override def work(i: Int): Int = {
    info(s"Start work with log and execute value $i")
    val result = super.work(i)
    info(s"End work with log $result")
    result
  }
}

val service3 = new LogServiceImportante("Hi")

(1 to 3).foreach (i => service3.work(i))
