package com.rbtree;

/**
 * Created by zhiwei on 2017/8/8.
 */
public class Node implements Cloneable {

    public int value;
    public int color;
    public Node left;
    public Node right;
    public Node parent;

    public Node(int v) {
        this.value = v;
    }

    public Node(int v, Node left, Node right) {
        this(v);
        this.left = left;
        this.right = right;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (Node) super.clone();
    }

}
