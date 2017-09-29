package com.segtree;


/**
 * Created by zhiwei on 2017/9/27.
 */
public class SegTree<T extends Number> {

    private Node<T> root;
    private static int INF = +100000000;

    public SegTree(T arr[]) {
        this.root = new Node<>(0, arr.length - 1);
        this.build(this.root, arr, 0, arr.length - 1);
    }

    @SuppressWarnings("")
    private T build(Node node, T arr[], int start, int end) {
        if (start == end) {
            node.value = arr[start];
        } else {
            //不断地将数组中的线段二分
            int mid = (start + end) / 2;
            node.left = new Node(start, mid);
            node.right = new Node(mid + 1, end);
            //在线段树上递归建立节点的过程
            T left = build(node.left, arr, start, mid);
            T right = build(node.right, arr, mid + 1, end);
            node.value = left.doubleValue() < right.doubleValue() ? left : right;
        }

        return (T) node.value;
    }

    private T queryMin(int qStart, int qEnd) {
        return query(this.root, qStart, qEnd);
    }

    private T query(Node node, int qStart, int qEnd) {
        if (node.low > qEnd || node.high < qStart) {
            return null;
        } else {
            if (node.low <= qStart && node.high >= qEnd) {
                // 查询线段包含在当前线段中
                return (T) node.value;
            } else {
                int mid = (qStart + qEnd) / 2;
                T left = query(node.left, qStart, mid);
                T right = query(node.right, mid + 1, qEnd);
                if (left == null) {
                    return right;
                } else if (right == null) {
                    return left;
                } else {
                    return left.doubleValue() < right.doubleValue() ? left : right;
                }
            }
        }
    }

    private double querySum(int sStart, int sEnd) {
        sStart = sStart < 0 ? 0 : sStart;
        sEnd = sEnd > this.root.high ? this.root.high : sEnd;
        return sum(this.root, sStart, sEnd);
    }

    private double sum(Node node, int sStart, int sEnd) {
        if (node.low > sEnd || node.high < sStart) {
            return 0.0f;
        } else {
            if (sStart == sEnd && node.isLeaf()) {
                return ((T) node.value).doubleValue();
            } else if (sStart == node.low && sEnd == node.high) {
                int mid = (sStart + sEnd) / 2;
                return sum(node.left, sStart, mid) + sum(node.right, mid + 1, sEnd);
            } else {
                int nMid = (node.low + node.high) / 2;
                if (nMid >= sEnd) {
                    return sum(node.left, sStart, sEnd);
                } else if (nMid <= sStart) {
                    return sum(node.right, sStart, sEnd);
                } else {
                    return sum(node.left, sStart, nMid) + sum(node.right, nMid + 1, sEnd);
                }
            }
        }
    }


    public static void main(String[] main) {
        Integer[] arr = {2, 5, 1, 4, 9, 3};

        SegTree<Integer> tree = new SegTree<>(arr);
        System.out.println(tree.queryMin(0, 2));
        System.out.println(tree.querySum(0, 6));
    }
}
