package com.coding.basic;

public class Queue<T> {

    private LinkedList<T> queue;

    public Queue() {
        queue = new LinkedList<>();
    }

    public void enQueue(T t) {
        queue.add(t);
    }

    public T deQueue() {
        return queue.removeLast();
    }

    public boolean isEmpty() {
        return queue.get(0) != null;
    }

    public int size() {
        return queue.size();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
