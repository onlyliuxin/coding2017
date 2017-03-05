package com.coding.basic;

public class LinkedList implements List {
    private int size = 0;
    private Node headNode;
    private Node lastNode;

    public LinkedList() {
        headNode = new Node();
        lastNode = new Node();
        headNode.next = lastNode;
        lastNode.prev = headNode;
    }

    public void add(Object o) {
        Node node = new Node();
        node.data = o;
        if (size == 0) {
            headNode.next = node;
            lastNode.prev = node;
            node.next = lastNode;
            node.prev = headNode;

        } else {
            Node tempLastNode = lastNode.prev;
            tempLastNode.next = node;

            node.next = lastNode;
            node.prev = tempLastNode;
            lastNode.prev = node;
        }
        size = size + 1;

    }

    public void add(int index, Object o) {
        checkRange(index);
        Node preNode = null;
        Node nextNode = null;
        for (int i = 0; i <= index; i++) {
            preNode = headNode.next;
        }
        nextNode = preNode.next;
        Node newNode = new Node();
        newNode.data = o;

        preNode.next = newNode;
        nextNode.prev = newNode;

        newNode.next = nextNode;
        newNode.prev = preNode;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    public void checkRange(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    public Object get(int index) {
        checkRange(index);
        Node node = null;
        for (int i = 0; i <= index; i++) {
            node = headNode.next;
        }
        return node;
    }

    public Object remove(int index) {
        return null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        Node oldFirstnode = headNode.next;
        Node node = new Node();
        node.data = o;
        headNode.next = node;
        node.next = oldFirstnode;
        node.prev = headNode;
        oldFirstnode.prev = node;
    }

    public void addLast(Object o) {
        Node oldLastNode = lastNode.prev;

        Node node = new Node();
        node.data = o;
        node.prev = oldLastNode;
        node.next = lastNode;

        oldLastNode.next = node;
        lastNode.prev = node;

    }

    public Object removeFirst() {
        Node firstNode = headNode.next;
        Node newFirstNode = firstNode.next;
        newFirstNode.prev = headNode;
        headNode.next = newFirstNode;
        return firstNode;
    }

    public Object removeLast() {
        Node lastOldNode = lastNode.prev;
        lastNode.prev = lastOldNode.prev;
        lastOldNode.prev.next = lastNode;

        return lastOldNode;
    }

    Iterator iterator = new MIterator();

    public Iterator iterator() {
        return iterator;
    }

    @Override
    public String toString() {
        return super.toString();

    }

    private static class Node {
        Object data;
        Node next;
        Node prev;
    }

    class MIterator implements Iterator {
        Node node;

        @Override
        public boolean hasNext() {
            if (node == null) {
                node = headNode.next;
            }
            if (!node.equals(lastNode)) {
                System.out.println("ture");

                return true;
            }
            node = null;
            return false;
        }

        @Override
        public Object next() {
            if (node.equals(headNode)) {
                node = headNode.next;
                return node;
            }
            Node tempNode = node;
            node = node.next;
            if (node == null) {
                throw new IndexOutOfBoundsException("out of ");
            }
            return tempNode.data;
        }

    }
}
