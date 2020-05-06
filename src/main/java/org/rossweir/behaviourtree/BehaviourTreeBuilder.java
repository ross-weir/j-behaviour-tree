package org.rossweir.behaviourtree;

import java.util.Stack;
import java.util.function.Function;
import org.rossweir.behaviourtree.Node.State;
import org.rossweir.behaviourtree.composites.SelectorComposite;
import org.rossweir.behaviourtree.composites.SequenceComposite;
import org.rossweir.behaviourtree.decorators.InverterDecorator;
import org.rossweir.behaviourtree.decorators.MaybeDecorator;
import org.rossweir.behaviourtree.decorators.RepeatUntilCountDecorator;
import org.rossweir.behaviourtree.decorators.SucceederDecorator;
import org.rossweir.behaviourtree.leafs.ActionLeaf;
import org.rossweir.behaviourtree.leafs.ConditionLeaf;

public final class BehaviourTreeBuilder<T> {
  private Node<T> currentNode;
  private final Stack<ParentNode<T>> parentNodeStack = new Stack<>();

  public BehaviourTreeBuilder<T> action(Function<T, State> task) {
    return registerChild(new ActionLeaf<T>().registerTask(task));
  }

  public BehaviourTreeBuilder<T> action(ActionLeaf<T> task) {
    return registerChild(task);
  }

  public BehaviourTreeBuilder<T> condition(Function<T, Boolean> task) {
    return registerChild(new ConditionLeaf<T>().registerTask(task));
  }

  public BehaviourTreeBuilder<T> condition(ConditionLeaf<T> task) {
    return registerChild(task);
  }

  public BehaviourTreeBuilder<T> sequence() {
    return registerParent(new SequenceComposite<>());
  }

  public BehaviourTreeBuilder<T> selector() {
    return registerParent(new SelectorComposite<>());
  }

  public BehaviourTreeBuilder<T> succeed() {
    return registerParent(new SucceederDecorator<>());
  }

  public BehaviourTreeBuilder<T> repeatUntilCount(int repeatCount) {
    return registerParent(new RepeatUntilCountDecorator<>(repeatCount));
  }

  public BehaviourTreeBuilder<T> invert() {
    return registerParent(new InverterDecorator<>());
  }

  public BehaviourTreeBuilder<T> maybe() {
    return registerParent(new MaybeDecorator<>());
  }

  public BehaviourTreeBuilder<T> insert(Node<T> subTree) {
    // TODO: handle empty parent stack.
    return registerChild(subTree);
  }

  public BehaviourTreeBuilder<T> finish() {
    currentNode = parentNodeStack.pop();

    return this;
  }

  public Node<T> build() {
    return currentNode;
  }

  private BehaviourTreeBuilder<T> registerChild(Node<T> node) {
    // TODO: ensure parent stack isn't empty.
    parentNodeStack.peek().registerChild(node);

    return this;
  }

  private BehaviourTreeBuilder<T> registerParent(ParentNode<T> node) {
    if (!parentNodeStack.empty()) {
      parentNodeStack.peek().registerChild(node);
    }

    parentNodeStack.push(node);

    return this;
  }
}
