package com.rbtree;


import java.util.ArrayList;

/**
 * Created by zhiwei on 2017/9/27.
 */

public class RBTree<T extends Comparable<T>> {


    public static final int RED = 0;
    public static final int BALCK = 1;

    private Node<T> root;
    private Node<T> nilT;

    public RBTree() {
        this.nilT = new Node<>();
        this.nilT.color = BALCK;
        this.nilT.right = this.nilT;
        this.nilT.left = this.nilT;
        this.root = nilT;
    }


    private void leftRotate(Node<T> x) {
        Node<T> y = x.right;
        x.right = y.left;
        if (y.left != this.nilT) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == this.nilT) {
            this.root = y;
        } else {
            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node<T> y) {
        Node<T> x = y.left;
        y.left = x.right;
        if (x.right != this.nilT) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == this.nilT) {
            this.root = x;
        } else {
            if (y == y.parent.left) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        }
        y.parent = x;
        x.right = y;
    }

    public void insert(T v) {
        Node<T> z = new Node<>(v, this.nilT, this.nilT);
        Node<T> y = this.nilT;
        Node<T> x = this.root;
        while (x != this.nilT) {
            y = x;
            int cmp = x.value.compareTo(z.value);
            if (cmp > 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y == this.nilT) {
            this.root = z;
        } else if (y.value.compareTo(z.value) > 0) {
            y.left = z;
        } else {
            y.right = z;
        }
        z.color = RED;
        this.rbInsertFixup(z);
    }

    private void rbInsertFixup(Node<T> z) {
        Node<T> y = null;
        while (z.parent != null && z.parent.color == RED) {
            //若插入父节点是其祖父节点的左孩子
            if (z.parent == z.parent.parent.left) {
                y = z.parent.parent.right;
                //插入节点的父节点和叔父节点都是红色
                if (y.color == RED) {
                    z.parent.color = BALCK;
                    y.color = BALCK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                    continue;
                    //叔父节点是黑色(这里按照算法导论默认nilT也是黑色的)插入节点是其父节点的右儿子,转化为case2或者case3中的一种情况
                } else if (z == z.parent.right) {
                    z = z.parent;
                    this.leftRotate(z);
                    //有可能旋转后就跳出循环，也可能转到case3,把当前节点座位父节点的右子
                }
                //叔父节点是黑色，插入节点是其父亲节点的左儿子
                z.parent.color = BALCK;
                z.parent.parent.color = RED;
                this.rightRotate(z.parent.parent);
            } else {
                y = z.parent.parent.left;
                if (y.color == RED) {
                    z.parent.color = BALCK;
                    y.color = BALCK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                    continue;
                } else if (z == z.parent.left) {
                    z = z.parent;
                    this.rightRotate(z);
                }
                z.parent.color = BALCK;
                z.parent.parent.color = RED;
                this.leftRotate(z.parent.parent);
            }
        }
        this.root.color = BALCK;
    }

    private Node<T> find(T v) {
        Node<T> z = this.root;
        while (z != this.nilT) {
            int cmp = z.value.compareTo(v);
            if (cmp == 0) {
                break;
            } else if (cmp < 0) {
                z = z.right;
            } else {
                z = z.left;
            }
        }
        return z;
    }

    private Node<T> getSuccessor(Node<T> z) {
        while (z.left != this.nilT) {
            z = z.left;
        }
        return z;
    }

    private void delete(T v) {
        Node<T> y = null;
        Node<T> z = this.find(v);
        if (z != this.nilT) {
            if (z.left == this.nilT || z.right == this.nilT) {
                y = z;
            } else {
                y = this.getSuccessor(z.right);
            }
            if (y == this.nilT) {
                this.root = y;
                return;
            }
            Node x = y.left != this.nilT ? y.left : y.right;
            x.parent = y.parent;
            if (y.parent == this.nilT) {
                this.root = x;
            } else if (y == y.parent.left) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }

            if (y != z) {
                //说明是删除节点中最复杂的一种情况双亲都有,复制y到对应的数据来填充z,颜色是保留z的
                z.value = y.value;
            }
            //被删除的节点是红色,是不会影响红黑树的性质的
            if (y.color == BALCK) {
                this.rbRemoveFixUp(x);
            }
        } else {
            System.out.println("can not find the value to delete");
        }
    }

    private void rbRemoveFixUp(Node<T> x) {
        while (x != this.root && x.color == BALCK) {
            //代替节点是x的左孩子
            if (x == x.parent.left) {
                Node w = x.parent.right;
                //当兄弟节点是红色的时候，把兄弟节点涂黑，转化为后面的几种情况，同时更新w的值
                if (w.color == RED) {
                    w.color = BALCK;
                    x.parent.color = RED;
                    this.leftRotate(x.parent);
                    w = x.parent.right;
                    //update the w
                }
                if (w.left.color == BALCK && w.right.color == BALCK) {
                    w.color = RED;
                    x = x.parent;
                } else {
                    //兄弟节点的右孩子是黑色，左孩子是红色-->转变为兄弟节点的右孩子是红色,左孩子是任意色
                    if (w.right.color == BALCK) {
                        w.left.color = BALCK;
                        w.color = RED;
                        this.rightRotate(w);
                        w = x.parent.right;
                        //update the w
                    }
                    w.color = w.parent.color;
                    x.parent.color = BALCK;
                    w.right.color = BALCK;
                    this.leftRotate(x.parent);
                    x = this.root;
                }
            } else {
                //与左孩子的情况是对应的
                Node w = x.parent.left;
                if (w.color == RED) {
                    w.color = BALCK;
                    x.parent.color = RED;
                    this.rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.left.color == BALCK && w.right.color == BALCK) {
                    if (w != this.nilT) {
                        w.color = RED;
                    }
                    x = x.parent;
                } else {
                    if (w.left.color == BALCK) {
                        w.right.color = BALCK;
                        w.color = RED;
                        this.leftRotate(w);
                        w = x.parent.left;
                        //update the w
                    }
                    w.color = w.parent.color;
                    x.parent.color = BALCK;
                    w.left.color = BALCK;
                    this.rightRotate(x.parent);
                    x = this.root;
                }
            }
            this.nilT.color = BALCK;
        }
        //代替节点为红色节点的情况
        x.color = BALCK;
    }


    public static void main(String[] args) {
        RBTree<Integer> tree = new RBTree<>();
        int[] numbers = {12, 1, 9, 2, 0, 11, 7, 19, 4, 15, 18, 5, 14, 13, 10, 16, 6, 3, 8, 17};
        for (int num : numbers) {
            tree.insert(num);
        }
        for (int num : numbers) {
            tree.delete(num);
        }
    }
}
