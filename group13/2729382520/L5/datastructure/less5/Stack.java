package io.github.vxzh.datastructure.less5;

import java.util.ArrayList;

public class Stack {

    private ArrayList elementData;

    public Stack() {
        this.elementData = new ArrayList();
    }

    public int size() {
        return elementData.size();
    }

    public boolean isEmpty() {
        return elementData.size() == 0;
    }

    public void push(Object o) {
        elementData.add(o);
    }

    public Object pop() {
        if (isEmpty())
            throw new RuntimeException("EmptyStackException");
        Object obj = peek();
        elementData.remove(elementData.size() - 1);
        return obj;
    }

    public Object peek() {
        if (isEmpty())
            throw new RuntimeException("EmptyStackException");
        return elementData.get(elementData.size() - 1);
    }

}