package datastructure.stack;

import java.util.EmptyStackException;

import datastructure.array.ArrayList;

public class Stack {
    private ArrayList elementData = new ArrayList();

    public void push(Object o) {
        elementData.add(o);
    }

    public Object pop() {
        if (elementData.size() == 0) {
            throw new EmptyStackException();
        }
        return elementData.remove(elementData.size() - 1);
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for ( ; i < elementData.size() - 1; i++) {
            builder.append(elementData.get(i) + ",");
        }
        builder.append(elementData.get(i));
        return builder.toString();
    }
    
}
