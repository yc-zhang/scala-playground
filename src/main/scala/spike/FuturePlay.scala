package spike

import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object FuturePlay {

  case class Connection(name: String)

  def connect(address: String): Future[Connection] = Future {
    println("I'm connecting")

    Thread.sleep(100)

    Connection("mysql")
  }

  def query(connection: Connection): Future[Seq[String]] = Future {
    println("I'm querying")

    Thread.sleep(100)

    Seq("record1", "record2", "record3")
  }

  def connect1(address: String): Future[Option[Connection]] = Future {
    println("I'm connecting")

    Thread.sleep(100)

    if (address == "mysql") Some(Connection("mysql")) else None
  }

  def query1(connection: Connection, index: Int): Future[Seq[String]] = Future {
    println(s"I'm querying with index $index")

    Thread.sleep(100)

    Seq(s"$index - record", s"$index - record", s"$index - record")
  }

  def case1 = {
    val result: Future[Connection] = connect("mysql")

    val connection: Connection = Await.result(result, 10 seconds)
    println(connection)
  }

  def case2 = {
    val result: Future[Connection] = connect("mysql")
    result.onComplete({
      case Success(connection) => println(s"You've connected with $connection")
      case Failure(e) => println(s"You got some error $e")
    })
  }

  def case3 = {
    val result: Future[Connection] = connect("mysql")
    val queryResult = result.flatMap(query)

    queryResult.onComplete({
      case Success(results) => println(s"results are $results")
      case Failure(e) => println(s"You got some error $e")
    })
  }

  def case4 = {
    val records = for {
      connection <- connect("mysql")
      queryRecords <- query(connection)
    } yield queryRecords

    records.onComplete({
      case Success(r) => println(s"results are $r")
      case Failure(e) => println(s"You got some error $e")
    })
  }

  def case5 = {
    val result: Future[Option[Connection]] = connect1("mysql")
    val records = result.map(t => {
      t.map(c => Seq("new record1", "new record2"))
    })

    records.onComplete({
      case Success(s) => println(s"You've connected with $s")
      case Failure(e) => println(s"You got some error $e")
    })
  }

  def case6 = {
    val connectionFuture = connect("mysql")
    connectionFuture.onSuccess {
      case c: Connection => {
        val futures: Seq[Future[Seq[String]]] = (1 to 5).map(i => query1(c, i))

        Future.sequence(futures).onComplete {
          case Success(results) => println(s"Results $results")
          case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
        }
      }
    }
  }

  def case7 = {
    val connectionFuture = connect("mysql")
    connectionFuture.onSuccess {
      case c: Connection => {
        val futures: Seq[Future[Seq[String]]] = (1 to 5).map(i => query1(c, i))

        val convert: Seq[String] => Seq[String] = s => s.map(s => s"changed: $s")

        Future.traverse(futures)(t => t.map(convert)).onComplete {
          case Success(results) => println(s"Results $results")
          case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
        }
      }
    }
  }

  def connectInSync(name: String, connectedMessageHandler: Promise[String]): Option[Connection] = {
    Thread.sleep(100)

    if (name == "mysql") {
      connectedMessageHandler.success("I'm connected!")
      Some(Connection(name))
    } else {
      connectedMessageHandler.failure(new Exception("unsupported database"))
      None
    }
  }

  def case8 = {
    val handler = Promise[String]

    handler.future.onComplete {
      case Success(s) => println(s"received: $s")
      case Failure(e) => println(s"error happens: $e")
    }

    connectInSync("mysql", handler)
  }

  def main(args: Array[String]): Unit = {
//    case1

//    case2

//    case3

//    case4

//    case5

//    case6

//    case7
//
//    case8
//
//    Thread.sleep(1000)
  }
}
