package org.rossweir.behaviourtree.decorators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.rossweir.behaviourtree.BehaviourTreeBuilder;
import org.rossweir.behaviourtree.Node;
import org.rossweir.behaviourtree.Node.State;
import org.rossweir.behaviourtree.TestBlackboard;

class RepeatUntilCountDecoratorTest {

  @ParameterizedTest
  @EnumSource(State.class)
  void testReturnsRunningUntilTickedCountAmountOfTimes(State state) {
    TestBlackboard blackboard = TestBlackboard.getBlackboard();
    Node<TestBlackboard> node = new BehaviourTreeBuilder<TestBlackboard>()
        .repeatUntilCount(4)
          .action(bb -> state)
        .finish()
        .build();

    assertEquals(State.RUNNING, node.tick(blackboard));
    assertEquals(State.RUNNING, node.tick(blackboard));
    assertEquals(State.RUNNING, node.tick(blackboard));
    assertEquals(state.RUNNING, node.tick(blackboard));
    assertEquals(state.SUCCESS, node.tick(blackboard));
  }
}
