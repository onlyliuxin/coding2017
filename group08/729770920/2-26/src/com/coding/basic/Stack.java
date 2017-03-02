package com.coding.basic;

public class Stack<E> {
    private LinkedList<E> data = new LinkedList<>();

    public void push(E e) {        
        data.addFirst(e);
    }
    
    public Object pop() {
        return data.removeFirst();
    }
    
    public Object peek() {
        return data.get(0);
    }

    public boolean isEmpty() {
        return data.size() == 0;
    }

    public int size() {
        return data.size();
    }
}
