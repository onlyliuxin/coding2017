package com.coding.basic;

public class Queue<E> {
    private LinkedList<E> data = new LinkedList<>();
    
    public void enQueue(E e){
        data.addFirst(e);
    }
    
    public Object deQueue() {
        return data.removeLast();
    }
    
    public boolean isEmpty() {
        return data.size() == 0;
    }

    public int size(){
        return data.size();
    }
}
