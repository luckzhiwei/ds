package com.rbtree;

/**
 * Created by zhiwei on 2017/8/8.
 */
public class Node<T extends Comparable<T>> implements Cloneable {

    public T value;
    public int color;
    public Node<T> left;
    public Node<T> right;
    public Node<T> parent;

    public Node() {

    }

    public Node(T v, Node left, Node right) {
        this.value = v;
        this.left = left;
        this.right = right;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (Node) super.clone();
    }

}