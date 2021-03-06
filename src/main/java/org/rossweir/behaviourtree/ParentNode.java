package org.rossweir.behaviourtree;

public abstract class ParentNode<T> extends Node<T> {
  public abstract <RT extends ParentNode<T>> RT registerChild(Node<T> child);
}
