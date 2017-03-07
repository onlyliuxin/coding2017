package com.circle.collection;

/**
 * Created by keweiyang on 2017/2/25.
 */
public class Queue {
    private LinkedList elementData = new LinkedList();

    public void enQueue(Object o) {

        elementData.addLast(o);
    }

    public Object deQueue() {
        return elementData.removeFirst();
    }

    public boolean isEmpty() {
        return elementData.size() <= 0;
    }

    public int size() {
        return elementData.size();
    }
}
