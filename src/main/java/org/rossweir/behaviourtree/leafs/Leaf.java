package org.rossweir.behaviourtree.leafs;

import java.util.function.Function;
import org.rossweir.behaviourtree.Node;

public abstract class Leaf<T, R> extends Node<T> {
  protected Function<T, R> task;

  public <RT extends Leaf<T, R>> RT registerTask(
      Function<T, R> task) {
    this.task = task;

    return (RT) this;
  }

  public abstract State tick(T blackboard);
}
