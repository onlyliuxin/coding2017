package com.coding.basic;

import java.util.EmptyStackException;

import com.coding.basic.array.ArrayList;

public class Stack {
    private ArrayList elementData = new ArrayList();

    public void push(Object o) {
        elementData.add(o);
    }

    public Object pop() {
        checkBound();
        return elementData.get(size() - 1);
    }

    public Object peek() {
        checkBound();
        return elementData.remove(size() - 1);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return elementData.size();
    }

    private void checkBound() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
    }
}
