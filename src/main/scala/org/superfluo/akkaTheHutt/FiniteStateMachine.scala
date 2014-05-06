package org.superfluo.akkaTheHutt

import akka.persistence.{Persistent, Processor}
import akka.actor.{Actor, Props, ActorSystem, LoggingFSM}

import scala.concurrent.duration._

/**
 * Created with IntelliJ IDEA.
 * User: pic
 * Date: 5/4/14
 * Time: 9:00 PM
 */

case class Done(newData: List[String])

class FiniteStateMachine extends Actor with LoggingFSM[String, List[String]] {

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

object Main extends App {

  val system = ActorSystem()

  system.actorOf(Props(new FiniteStateMachine), "underTest")

}