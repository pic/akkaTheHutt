package org.superfluo.akkaTheHutt

import akka.testkit.TestFSMRef
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * Created with IntelliJ IDEA.
 * User: pic
 * Date: 5/6/14
 * Time: 8:35 AM
 */
@RunWith(classOf[JUnitRunner])
class PersistentFSMSpec extends FunKit {

  describe("FiniteStateMachine") {

    before {
    }

    after {
    }

    def createFsmRef = TestFSMRef(new PersistentFSM)


    it("should almost immediately create a new string and move to Waiting as soon as this is done") {
      val fsmRef = createFsmRef

      Thread.sleep(100)

      assert(fsmRef.stateName === "waiting")
      assert(fsmRef.stateData.size === 1)
    }
  }
}