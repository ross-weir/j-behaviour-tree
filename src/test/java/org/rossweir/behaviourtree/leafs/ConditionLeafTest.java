package org.rossweir.behaviourtree.leafs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.rossweir.behaviourtree.Node.State;
import org.rossweir.behaviourtree.TestBlackboard;

class ConditionLeafTest {

  @Test
  void testReturnsSuccessStateIfTaskReturnedTrue() {
    ConditionLeaf<TestBlackboard> node = new ConditionLeaf<TestBlackboard>().registerTask(bb -> true);

    assertEquals(State.SUCCESS, node.tick(TestBlackboard.getBlackboard()));
  }

  @Test
  void testReturnsFailedStateIfTaskReturnedFalse() {
    ConditionLeaf<TestBlackboard> node = new ConditionLeaf<TestBlackboard>().registerTask(bb -> false);

    assertEquals(State.FAILED, node.tick(TestBlackboard.getBlackboard()));
  }
}
