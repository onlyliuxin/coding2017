package com.datastructures.basic;

public class Stack<E> {
    private LinkedList<E> data = new LinkedList<>();

    public void push(E e) {        
        data.addFirst(e);
    }
    
    public E pop() {
        return data.removeFirst();
    }
    
    public E peek() {
        return data.get(0);
    }

    public boolean isEmpty() {
        return data.size() == 0;
    }

    public int size() {
        return data.size();
    }
}
