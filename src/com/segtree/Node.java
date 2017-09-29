package com.segtree;

/**
 * Created by zhiwei on 2017/9/27.
 */
public class Node<T> {

    public T value;
    public int low;
    public int high;

    public Node<T> left;
    public Node<T> right;

    public Node(int low, int high) {
        this.low = low;
        this.high = high;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }


}
