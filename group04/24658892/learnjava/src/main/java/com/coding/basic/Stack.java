package com.coding.basic;

import java.util.EmptyStackException;

public class Stack {

    private ArrayList elementData = new ArrayList();
    private int size = 0;

    public void push(Object o) {
        elementData.add(o);
        size++;
    }

    public Object pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elementData.remove(--size);
    }

    public Object peek() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return elementData.get(size - 1);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
