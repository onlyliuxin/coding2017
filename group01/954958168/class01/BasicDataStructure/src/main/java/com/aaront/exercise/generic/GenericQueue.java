package com.aaront.exercise.generic;

public class GenericQueue<T> {

    private GenericLinkedList<T> linkedList = new GenericLinkedList<>();

    public void enQueue(T o) {
        linkedList.add(o);
    }

    public T deQueue() {
        return linkedList.removeFirst();
    }

    public boolean isEmpty() {
        return linkedList.size() == 0;
    }

    public int size() {
        return linkedList.size();
    }

    public Object[] toArray() {
        return linkedList.toArray();
    }

    public T[] toArray(T[] a) {
        return linkedList.toArray(a);
    }
}
