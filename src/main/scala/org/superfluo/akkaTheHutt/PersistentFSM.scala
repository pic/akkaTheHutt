package org.superfluo.akkaTheHutt

import akka.actor.{ActorSystem, Props, LoggingFSM, Actor}
import scala.concurrent.duration._
import akka.persistence.{Persistent, Processor}

/**
 * Created with IntelliJ IDEA.
 * User: pic
 * Date: 5/5/14
 * Time: 12:09 AM
 */
class PersistentFSM extends Processor with LoggingFSM[String, List[String]] {

  startWith("waiting", List(), Some(1.nanosecond))

  val rand = new scala.util.Random

  when("doingSomething") {
    case Event(Persistent(Done(newData), _), _) => goto("waiting").using(newData)
  }

  when("waiting", stateTimeout = 1.second) {
    case Event(StateTimeout, _) => {
      println(stateData)
      goto("doingSomething")
    }
  }

  onTransition {
    case _ -> "doingSomething" => {
      self ! Persistent(Done(rand.nextString(5) :: stateData))
    }
  }

  initialize

}

object PersistentMain extends App {

  val system = ActorSystem()

  system.actorOf(Props(new PersistentFSM), "persistentUnderTest")

}
