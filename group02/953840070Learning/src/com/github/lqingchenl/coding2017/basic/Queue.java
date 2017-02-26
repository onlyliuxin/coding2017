package com.github.lqingchenl.coding2017.basic;

import org.junit.Test;

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

    @Test
    public void testStack() {
        enQueue(1);
        enQueue(2);
        enQueue(3);
        enQueue(4);
        System.out.println(deQueue()); //出队列第一个元素
        System.out.println(size());
    }
}
