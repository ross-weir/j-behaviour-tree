package org.rossweir.behaviourtree.leafs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.rossweir.behaviourtree.Node.State;
import org.rossweir.behaviourtree.TestBlackboard;

class ActionLeafTest {

  @DisplayName("Should return state")
  @ParameterizedTest(name = "{index} => state = {0}")
  @EnumSource(State.class)
  void testReturnsCorrectState(State expectedState) {
    ActionLeaf<TestBlackboard> node = new ActionLeaf<TestBlackboard>().registerTask(bb -> expectedState);

    assertEquals(expectedState, node.tick(TestBlackboard.getBlackboard()));
  }
}
