package com.github.lhpmatlab.coding2017.basic;

/**
 * Created by andy on 2017/2/22.
 */
public class MyQueue<T> {
    private MyLinkedList<T> link = new MyLinkedList<>();

    public void enQueue(T t) {
        link.add(t);
    }

    public T deQueue() {
        if (size() <= 0) {
            return null;
        }
        T t = link.get(0);
        link.remove(0);
        return t;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return link.size();
    }
}
