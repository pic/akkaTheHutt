package org.superfluo.akkaTheHutt

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import akka.testkit.TestFSMRef

/**
 * Created with IntelliJ IDEA.
 * User: pic
 * Date: 5/6/14
 * Time: 9:23 AM
 */
@RunWith(classOf[JUnitRunner])
class AlmostPersistentFSMSpec extends FunKit {

  describe("FiniteStateMachine") {

    before {
    }

    after {
    }

    def createFsmRef = TestFSMRef(new AlmostPersistentFSM)


    it("should almost immediately create a new string and move to Waiting as soon as this is done") {
      val fsmRef = createFsmRef

      Thread.sleep(100)

      assert(fsmRef.stateName === "waiting")
      assert(fsmRef.stateData.size === 1)
    }
  }
}