package com.coding.basic;

public class Stack {
    private ArrayList elementData = new ArrayList();

    public void push(Object o) {
		elementData.add(o);
    }

    public Object pop() {
        int index = elementData.size() - 1;
        return elementData.remove(index);
    }

    public Object peek() {
        int index = elementData.size() - 1;
        return elementData.get(index);
    }

    public boolean isEmpty() {
        return elementData.size() == 0;
    }

    public int size() {
        return elementData.size();
    }
}
