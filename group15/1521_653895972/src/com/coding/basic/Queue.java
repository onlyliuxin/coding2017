package com.coding.basic;

public class Queue {
    private LinkedList elementData = new LinkedList();

    public void enQueue(Object o) {
        elementData.add(o);
    }

    public Object deQueue() {
        return elementData.removeFirst();
    }

    public boolean isEmpty() {
        return elementData.size()==0?true:false;
    }

    public int size() {
        return elementData.size();
    }

    @Override
    public String toString() {
        return "Queue{" +
                "elementData=" + elementData +
                '}';
    }
}
