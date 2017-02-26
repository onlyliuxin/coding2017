package com.github.lqingchenl.coding2017.basic;

import org.junit.Test;

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

    @Test
    public void testStack(){
        push(1);
        push(2);
        push(3);
        push(4);
        System.out.println(pop());
        System.out.println(peek());
        System.out.println(isEmpty());
        System.out.println(size());
    }
}
