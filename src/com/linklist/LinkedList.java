package com.linklist;

/**
 * Created by zhiwei on 2018/1/4.
 */
public class LinkedList<E> {

    private int size;
    private Node<E> header;
    private Node<E> current;

    public static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }
    }

    public E get(int index) {
        Node<E> node = entry(index);
        return node.value;
    }

    public void set(int index, E value) {
        Node node = entry(index);
        node.value = value;
    }

    public void add(int value) {
        if (header == null) {
            header = new Node(value);
            current = header;
        } else {
            current.next = new Node(value);
            current = current.next;
        }
        this.size++;
    }

    public void clear() {
        Node node = header;
        while (node != null) {
            node.value = null;
        }
        header = null;
        current = null;
        System.gc();
    }

    public void remove(int index) {
        if (index < 0 || index >= size) return;
        if (index == 0) {
            this.header = this.header.next;
            return;
        }
        Node node = header;
        for (int i = 0; i < index - 1; i++) {
            node = node.next;
        }
        node.next = node.next.next;
        if (node.next == null) {
            current = node;
        }
        size--;
    }


    private Node entry(int index) {
        if (index < 0 || index >= size) return null;
        Node node = header;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public int size() {
        return this.size;
    }


    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.set(1, 3);
        linkedList.remove(1);
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }
    }
}
