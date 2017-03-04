package com.coding.basic;

public class Stack {
    private ArrayList elementData = new ArrayList();

    public void push(Object o) {
        elementData.add(o);
    }

    public Object pop() {
        Object object = elementData.get(elementData.size() - 1);
        elementData.remove(elementData.size() - 1);
        return object;
    }

    public Object peek() {
        Object object = elementData.get(elementData.size() - 1);
        return object;
    }

    public boolean isEmpty() {
        if (elementData.size() > 0) {
            return false;
        }
        return true;

    }

    public int size() {
        return elementData.size();
    }

    Iterator iterator = elementData.iterator();
}
