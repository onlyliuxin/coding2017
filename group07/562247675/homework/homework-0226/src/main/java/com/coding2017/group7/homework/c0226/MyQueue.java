package com.coding2017.group7.homework.c0226;

import java.util.EmptyStackException;

public class MyQueue {

    private MyLinkedList myLinkedList = new MyLinkedList();

    public void enQueue(Object o) {
        myLinkedList.add(0, o);
    }

    public Object deQueue() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return myLinkedList.removeLast();
    }

    public boolean isEmpty() {
        return myLinkedList.size() <= 0;
    }

    public int size() {
        return myLinkedList.size();
    }

    private static class EmptyQueueException extends EmptyStackException {
    }

    @Override
    public String toString() {
        return myLinkedList.toString();
    }
}
