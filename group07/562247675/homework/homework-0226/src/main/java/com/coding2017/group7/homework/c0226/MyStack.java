package com.coding2017.group7.homework.c0226;

import java.util.EmptyStackException;

public class MyStack {
    private MyArrayList elementData = new MyArrayList();
    private final int first = 0;

    public void push(Object o) {

        elementData.add(first, o);
    }

    public Object pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elementData.remove(first);
    }

    public Object peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elementData.get(first);
    }

    public boolean isEmpty() {
        return elementData.size() <= 0;
    }

    public int size() {
        return elementData.size();
    }

    @Override
    public String toString() {
        return elementData.toString();
    }
}
