package examples.akkacluster.remote

import akka.actor.Actor


class Worker extends Actor {
  import Worker._

  def receive: Receive = {
    case msg: Work =>
      println(s"I received Work Message and My ActorRef: $self")
  }
}


object Worker {

  case class Work(message: String)
}
