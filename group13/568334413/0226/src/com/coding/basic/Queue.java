package com.coding.basic;

public class Queue {
    LinkedList linkedList = new LinkedList();

    public void enQueue(Object o) {
        linkedList.addLast(o);
    }

    public Object deQueue() {
        Object o = linkedList.removeLast();
        return o;
    }

    public boolean isEmpty() {
        if (linkedList.size() == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return linkedList.size();
    }

    public  Iterator iterator = linkedList.iterator();
}
