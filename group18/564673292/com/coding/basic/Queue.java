package com.coding.basic;

public class Queue<E> {
    private LinkedList<E> linkedList;

    // constructor
    public Queue(){
        linkedList = new LinkedList<E>();
    }

    public void enQueue(E o){
        linkedList.addLast(o);
    }
    
    public E deQueue(){
        return linkedList.removeFirst();
    }

    public E peek(){
        return linkedList.get(0);
    }
    
    public boolean isEmpty(){
        return linkedList.size() == 0 ? true : false;
    }
    
    public int size(){
        return linkedList.size();
    }
}