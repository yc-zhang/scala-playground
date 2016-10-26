import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

def sleep(millis: Long): Unit = {
  Thread.sleep(millis)
}

def doWork(index: Int) = {
  sleep((Math.random() * 1000).toLong)
  index
}

val pf2: PartialFunction[Throwable, Unit] = {
  case th: Throwable => println(s"FAILURE! returned: $th")
}

1 to 5 foreach { index =>
  val f = Future {
    doWork(index)
  }
  f onSuccess {
    case answer: Int => println(s"Success! returned: $answer")
  }
  f onFailure pf2
}

sleep(1000)

println("Finish")
