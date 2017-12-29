package com.arraylist;

import java.util.Arrays;

/**
 * Created by zhiwei on 2017/10/12.
 */
public class ArrayList<T> {


    private T[] content;
    private int capacity;
    private int size;

    public ArrayList() {
        this(10);
    }

    public ArrayList(int n) {
        this.capacity = n;
        this.content = (T[]) new Object[n];
    }

    public void add(T element) {
        if (size + 1 >= capacity) {
            extentCapacity();
        }
        this.content[size++] = element;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return this.content[index];
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        for (int i = index; i < size; i++) {
            this.content[i] = this.content[i + 1];
        }
    }

    public void set(int index, T element) {
        if (index >= size || index < 0) {
            return;
        }
        this.content[index] = element;
    }

    public void add(int index, T element) {
        if (index > this.size || index < 0) {
            return;
        }
        if (index == size) {
            this.add(element);
        } else {
            insert(index, element);
        }
    }

    private void insert(int index, T element) {
        if (size + 1 >= capacity) {
            extentCapacity();
        }
        for (int i = size; i > index; i--) {
            this.content[i] = this.content[i - 1];
        }
        this.content[index] = element;
        size++;
    }

    public void clear() {
        for (int i = 0; i < size; i++)
            this.content[i] = null;
        size = 0;
    }

    private void extentCapacity() {
        this.capacity = (3 * this.capacity) / 2 + 1;
        this.content = Arrays.copyOf(this.content, this.capacity);
    }

    private int size() {
        return this.size;
    }


    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            list.add(i);
        list.add(1, 100);
        for (int i = 0; i < 11; i++)
            System.out.println(list.get(i));
        System.out.println();

    }


}
