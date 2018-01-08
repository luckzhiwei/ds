package com.stack;

import java.util.Arrays;

/**
 * Created by zhiwei on 2018/1/8.
 */
public class Stack<E> {

    private E content[];
    private int top;
    private int capacity;

    public Stack() {
        this(1);
    }

    public Stack(int n) {
        this.content = (E[]) new Object[n];
        this.capacity = n;
        this.top = -1;
    }

    public void push(E ele) {
        if (top + 1 >= capacity) {
            extentCapacity();
        }
        this.content[++top] = ele;
    }

    public E pop() {
        if (top == -1) {
            return null;
        }
        E ret = this.content[top];
        this.content[top] = null;
        top--;
        return ret;
    }

    public E peek() {
        if (top == -1) {
            return null;
        }
        return this.content[top];
    }

    private void extentCapacity() {
        this.capacity = (3 * this.capacity) / 2 + 1;
        this.content = Arrays.copyOf(this.content, this.capacity);
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public int size() {
        return this.top;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }


}
