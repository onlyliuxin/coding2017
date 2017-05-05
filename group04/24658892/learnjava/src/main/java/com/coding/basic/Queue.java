package com.coding.basic;

import java.util.EmptyStackException;

public class Queue {

    private ArrayList elementData = new ArrayList();
    private int size = 0;

    public void enQueue(Object o) {
        elementData.add(o);
        size++;
    }

    public Object deQueue() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        size--;
        return elementData.remove(0);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
