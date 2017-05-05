package com.coding.basic;

public class Stack {
    private ArrayList elementData = new ArrayList();

    public void push(Object o) {
        elementData.add(0, o);
    }

    public Object pop() {
        return elementData.remove(0);
    }

    public Object peek() {
        return elementData.get(0);
    }

    public boolean isEmpty() {
        return elementData.size() == 0 ? true : false;
    }

    public int size() {
        return elementData.size();
    }
}
