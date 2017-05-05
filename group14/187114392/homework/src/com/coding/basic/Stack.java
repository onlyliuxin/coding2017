package com.coding.basic;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.EmptyStackException;

public class Stack {
    private ArrayList elementData = new ArrayList();

    public void push(Object o) {
        elementData.add(o);
//        System.out.println("size is " + elementData.size());
    }

    public Object pop() {
        int elementData_size = elementData.size();
        if (elementData_size == 0) {
            throw new EmptyStackException();
        }
        Object top_data = elementData.get(elementData_size - 1);
        elementData.remove(elementData_size - 1);
        return top_data;
    }

    public Object peek() {
        if (elementData.size() == 0) {
            throw new EmptyStackException();
        }
        return elementData.get(elementData.size() - 1);
    }

    public boolean isEmpty() {
        return elementData.size() == 0;
    }

    public int size() {
        return elementData.size();
    }
}
