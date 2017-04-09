package com.coding.basic.stack;

import java.util.List;

import com.google.common.collect.Lists;

@SuppressWarnings("unchecked")
public class Stack {

    private List elementData = Lists.newArrayList();

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
        return elementData.isEmpty();
    }

    public int size() {
        return elementData.size();
    }
}
