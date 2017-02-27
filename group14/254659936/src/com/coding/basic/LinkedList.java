package com.coding.basic;

public class LinkedList implements List {

    private Node head;
    private int size = 0;

    public void add(Object o) {
        addLast(o);
    }

    public void add(int index, Object o) {
        if (index >= size) {
            throw new RuntimeException("the LinkedList size is short than index");
        }
        Node node = new Node();
        node.data = 0;
        if (index == 0) {
            node.next = head;
            head = node;
        }
        Node next = head;
        int i = 0;
        while (null != next) {
            i++;
            if (index == i) {
                node.next = next.next;
                next.next = node;
                break;
            }
            next.next = next.next;
        }
        size++;
    }

    public Object get(int index) {
        if (index >= size) {
            throw new RuntimeException("the LinkedList size is short than index");
        }
        Node next = head;
        int i = 0;
        while (null != next) {
            if (i == index) {
                return next;
            }
            next = next.next;
            i++;
        }
        return null;
    }

    public Object remove(int index) {
        if (index >= size) {
            throw new RuntimeException("the LinkedList size is short than index");
        }
        size--;
        Node resultNode = null;
        if (index == 0) {
            resultNode = head;
            head = head.next;
            return resultNode.data;
        }

        Node next = head;
        int i = 0;
        while (null != next) {
            if (i == index) {
                resultNode = next.next;
                next.next = resultNode.next;
            }
        }
        return resultNode.data;
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        size++;
        Node node = new Node();
        node.data = 0;
        node.next = head;
        head = node;
    }

    public void addLast(Object o) {
        size++;
        Node node = new Node();
        node.data = o;
        if (null == head) {
            head = node;
            return;
        }
        Node next = head;
        while (null != next.next) {
            next = next.next;
        }
        next.next = node;
    }

    public Object removeFirst() {
        if (size == 0) {
            throw new RuntimeException("the LinkedList is null");
        }
        size--;
        Object obj = head.data;
        head = head.next;
        return obj;
    }

    public Object removeLast() {
        if (size == 0) {
            throw new RuntimeException("the LinkedList is null");
        }
        Node next = head;
        Object obj = null;
        for (int i = 0; i < size - 1; i++) {
            next = next.next;
        }
        next.next = null;
        size--;
        return obj;
    }

    public Iterator iterator() {
        return null;
    }


    private static class Node {
        Object data;
        Node next;
    }

    private class LinkedListIterator implements Iterator {

        private Node next = head;

        @Override
        public boolean hasNext() {
            return null != next;
        }

        @Override
        public Object next() {
            if (null == next) {
                throw new RuntimeException("the LinkedList is out of index");
            }
            Object obj = next.data;
            next = next.next;
            return obj;
        }
    }
}
