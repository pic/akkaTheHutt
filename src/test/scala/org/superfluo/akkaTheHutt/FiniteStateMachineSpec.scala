package org.superfluo.akkaTheHutt

import org.scalatest.{BeforeAndAfterAll, BeforeAndAfter, FunSpec}
import akka.actor.ActorSystem
import akka.testkit.{TestKitBase, TestFSMRef}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

class FunKit(implicit val system: ActorSystem = ActorSystem()) extends FunSpec with BeforeAndAfter with BeforeAndAfterAll with TestKitBase

/**
 * Created with IntelliJ IDEA.
 * User: pic
 * Date: 5/6/14
 * Time: 7:59 AM
 */
@RunWith(classOf[JUnitRunner])
class FiniteStateMachineSpec extends FunKit {

  describe("FiniteStateMachine") {

    before {
    }

    after {
    }

    def createFsmRef = TestFSMRef(new FiniteStateMachine)


    it("should almost immediately create a new string and move to Waiting as soon as this is done") {
      val fsmRef = createFsmRef

      Thread.sleep(100)

      assert(fsmRef.stateName === "waiting")
      assert(fsmRef.stateData.size === 1)
    }
  }
}
