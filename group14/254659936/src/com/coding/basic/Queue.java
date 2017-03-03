package com.coding.basic;

public class Queue {

    private Node head;
    private Node tail;
    private int size;

    public void enQueue(Object o) {
        Node node = new Node();
        node.data = o;
        if (null == head) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
        size++;
    }

    public Object deQueue() {
        if (size <= 0) {
            throw new RuntimeException("the queue is empty");
        }
        Object obj = head.data;
        head = head.next;
        size--;
        return obj;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private static class Node {
        Object data;
        Node next;
        Node pre;
    }
}
