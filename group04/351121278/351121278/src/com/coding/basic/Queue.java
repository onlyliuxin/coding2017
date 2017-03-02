package com.coding.basic;

public class Queue {

    private ArrayList elementData = new ArrayList();

    public void enQueue(Object o){
        elementData.add(o);
    }

    public Object deQueue(){
        Object remove = elementData.remove(0);
        return remove;
    }

    public boolean isEmpty(){
        return elementData.size() == 0;
    }

    public int size(){
        return elementData.size();
    }
}