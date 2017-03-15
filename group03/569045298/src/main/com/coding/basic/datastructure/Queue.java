package com.coding.basic.datastructure;


/**
 * Created by zt on 2017/2/19.
 */
public class Queue {

    private ArrayList elementData;

    private int size;

    public Queue() {
        elementData = new ArrayList();
    }

    public void enQueue(Object object) {
        elementData.add(object);
        size++;
    }

    public Object deQueue() {
        checkIsEmpty();
        Object object = elementData.get(0);
        elementData.remove(0);
        size--;
        return object;
    }

    private void checkIsEmpty() {
        if (elementData.size() == 0) {
            throw new RuntimeException("queue is empty");
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }
}
