package org.superfluo.akkaTheHutt

import akka.actor.{Props, ActorSystem, LoggingFSM, Actor}
import akka.actor.FSM.{->, Event}

import scala.concurrent.duration._
import akka.persistence.Processor

/**
 * Created with IntelliJ IDEA.
 * User: pic
 * Date: 5/6/14
 * Time: 9:21 AM
 */
class AlmostPersistentFSM extends Processor with LoggingFSM[String, List[String]] {

  startWith("waiting", List(), Some(1.nanosecond))

  val rand = new scala.util.Random

  when("doingSomething") {
    case Event(Done(newData), _) => goto("waiting").using(newData)
  }

  when("waiting", stateTimeout = 1.second) {
    case Event(StateTimeout, _) => {
      println(stateData)
      goto("doingSomething")
    }
  }

  onTransition {
    case _ -> "doingSomething" => {
      self ! Done(rand.nextString(5) :: stateData)
    }
  }

  initialize

}

object AlmostPersistentMain extends App {

  val system = ActorSystem()

  system.actorOf(Props(new AlmostPersistentFSM), "almostPersistentUnderTest")

}
