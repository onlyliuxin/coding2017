package com.github.lqingchenl.coding2017.basic;

public class Queue {
    private LinkedList queue = new LinkedList();
    private int size;

    public void enQueue(Object o) {
        queue.addLast(o);
        size++;
    }

    public Object deQueue() {
        Object o = queue.removeFirst();
        size--;
        return o;
    }

    public boolean isEmpty() {
        if (queue.size() == 0)
            return true;
        return false;
    }

    public int size() {
        return size;
    }

}
