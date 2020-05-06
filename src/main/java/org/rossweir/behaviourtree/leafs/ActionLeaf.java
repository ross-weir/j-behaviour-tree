package org.rossweir.behaviourtree.leafs;

import org.rossweir.behaviourtree.Node.State;

public class ActionLeaf<T> extends Leaf<T, State> {

  @Override
  public State tick(T blackboard) {
    return task.apply(blackboard);
  }
}
