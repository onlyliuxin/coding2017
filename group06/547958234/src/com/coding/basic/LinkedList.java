package com.coding.basic;

import sun.jvm.hotspot.debugger.win32.coff.AuxBfEfRecord;

public class LinkedList implements List {

    private Node head;
    private int size;

    public void add(Object o) {
        if (this.size == 0) {
            addFirst(o);
        } else {
            Node node = findNode(size - 1);
            Node newNode = new Node();
            newNode.data = o;
            newNode.next = null;
            node.next = newNode;
            this.size++;
        }
    }

    public void add(int index, Object o) {
        ensureNoOverStep(index);
        if (index == 0) {
            addFirst(o);
        } else if (index == this.size) {
            addLast(o);
        } else {
            Node beforeNode = findNode(index - 1);
            Node newNode = new Node();
            newNode.data = o;
            newNode.next = beforeNode.next;
            beforeNode.next = newNode;
            this.size++;
        }

    }

    public Object get(int index) {
        ensureNoOverStep(index);
        Node node = findNode(index);
        return node.data;
    }

    public Object remove(int index) {
        //只需要把对应节点从链表中摘出来就行了？
        ensureNoOverStep(index);
        if (index == 0) {
            return removeFirst();
        } else if (index == this.size - 1) {
            return removeLast();
        } else {
            Node beforeNode = findNode(index - 1);
            Node selectNode = beforeNode.next;
            beforeNode.next = selectNode.next;
            this.size--;
            return selectNode.data;
        }
    }

    public int size() {
        return this.size;
    }

    public void addFirst(Object o) {
        Node node = new Node();
        node.data = o;
        node.next = this.head;
        this.head = node;
        this.size++;
    }

    public void addLast(Object o) {
        add(o);
    }

    public Object removeFirst() {
        Node node = new Node();
        node = head;
        head = head.next;
        this.size--;
        return node.data;
    }

    public Object removeLast() {
        Node beforeNode = findNode(this.size - 2);
        Node node  = beforeNode.next;
        beforeNode.next = null;
        this.size--;
        return node.data;
    }

    public Iterator iterator() {
        return null;
    }

    private static class Node {
        Object data;
        Node next;
    }

    private Node findNode(int index) {
        ensureNoOverStep(index);
        Node node = this.head;
        while (index > 0) {
            node = node.next;
            index--;
        }
        return node;
    }

    private void ensureNoOverStep(int index) {
        if (index > this.size) throw new IndexOutOfBoundsException("Index: " + index + ", size: " + this.size);
    }
}
