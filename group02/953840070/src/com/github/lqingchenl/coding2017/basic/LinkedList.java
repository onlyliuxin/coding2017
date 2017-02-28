package com.github.lqingchenl.coding2017.basic;

public class LinkedList implements List {

    private int size = 0;

    private Node head;

    public void add(Object o) {
        if (head == null) {
            head = new Node(o);
        } else {
            Node nextNode = head;
            while (nextNode.next != null) {
                nextNode = nextNode.next;
            }
            nextNode.next = new Node(o);
        }
        size++;
    }

    public void add(int index, Object o) {
        if (index == 0) {
            addFirst(o);
            return;
        }
        Node oldNode = getNode(index - 1);
        Node newNode = new Node(o);
        newNode.next = oldNode.next;
        oldNode.next = newNode;
        size++;
    }

    public Object get(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }

    public Node getNode(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public Object remove(int index) {
        if (index == 1) {
            return removeFirst();
        }
        Node fatherNode = getNode(index - 2);
        Node oldNode = getNode(index - 1);
        fatherNode.next = oldNode.next;
        size--;

        return oldNode.data;
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        Node newNode = new Node(o);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(Object o) {
        if (head == null) {
            addFirst(o);
            return;
        }
        Node newNode = new Node(o);
        Node lastNode = getNode(size - 1);
        lastNode.next = newNode;
        size++;
    }

    public Object removeFirst() {
        Node oldHead = head;
        Node secondNode = head.next;
        head = secondNode;
        size--;
        return oldHead.data;
    }

    public Object removeLast() {
        if (size == 1) {
            return removeFirst();
        }
        Object data = get(size - 1);
        Node oldNode = getNode(size - 2);
        oldNode.next = null;
        size--;
        return data;
    }

    public Iterator iterator() {
        return null;
    }


    private static class Node {
        Object data;
        Node next;

        public Node(Object data) {
            this.data = data;
        }

    }

}
