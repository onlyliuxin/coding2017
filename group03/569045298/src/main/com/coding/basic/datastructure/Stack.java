package com.coding.basic.datastructure;

/**
 * Created by zt on 2017/2/19.
 */
public class Stack {

    private ArrayList elementData = null;

    private int size = 0;

    public Stack() {
        elementData = new ArrayList();
    }

    public void push(Object object) {
        elementData.add(object);
        size++;
    }

    public Object pop() {
        checkIsEmpty();
        Object peekObject = peek();
        elementData.remove(size - 1);
        size--;
        return peekObject;
    }

    public Object peek() {
        checkIsEmpty();
        return elementData.get(size - 1);
    }

    private void checkIsEmpty() {
        if (isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }
}
