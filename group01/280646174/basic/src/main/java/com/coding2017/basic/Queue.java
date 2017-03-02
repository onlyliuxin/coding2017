package com.coding2017.basic;

public class Queue {

    LinkedList list = new LinkedList();

    public void enQueue(Object o) {
        list.addLast(o);
    }

    public Object deQueue() {
        return list.removeFirst();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }
}
