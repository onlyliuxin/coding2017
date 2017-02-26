package com.github.lqingchenl.coding2017.basic;

public class Stack {
    private ArrayList elementData = new ArrayList();
    private int size = 0;

    public void push(Object o) {
        elementData.add(o);
        size++;
    }

    public Object pop() {
        Object o =  elementData.get(size - 1);
        elementData.remove(size - 1);
        size--;
        return o;
    }

    public Object peek() {
        return elementData.get(size - 1);
    }

    public boolean isEmpty() {
        if (elementData.size() == 0)
            return true;
        return false;
    }

    public int size() {
        return size;
    }

}
