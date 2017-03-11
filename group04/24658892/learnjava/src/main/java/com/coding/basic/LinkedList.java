package com.coding.basic;

public class LinkedList implements List {

    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
        head = new Node(null, null);
        tail = head;
    }

    public void add(Object o) {
        tail = new Node(o, null);
        if (head.next == null) {
            head.next = tail;
        }
        else {
            Node node = head.next;
            int i = 0;
            while (node.next != null && i < size) {
                node = node.next;
                i++;
            }
            node.next = tail;
        }
        size++;
    }

    public void add(int index, Object o) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(o);
        }
        else if (index == size) {
            addLast(o);
        }
        else {
            Node node = head.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            node.next = new Node(o, node.next);
        }
        size++;
    }

    public Object get(int index) {
        return null;
    }

    public Object remove(int index) {
        return null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        head.next = new Node(o, head.next);
        size++;
    }

    public void addLast(Object o) {
        Node node = tail;
        tail = new Node(o, null);
        node.next = tail;
        size++;
    }

    public Object removeFirst() {
        if (head.next == null) {
            throw new IndexOutOfBoundsException();
        }
        Node node = head.next;
        head.next = node.next;
        node.next = null;
        return node;
    }

    public Object removeLast() {
        return null;
    }

    public Iterator iterator() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (size > 0) {
            Node node = head.next;
            int i = 0;
            while (node.next != null && i < size) {
                sb.append("index=").append(i).append(";value=").append(node.data).append("\n");
                node = node.next;
                i++;
            }
            sb.append("index=").append(i).append(";value=").append(node.data).append("\n");
        }
        return sb.toString();
    }

    private static class Node {

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        Object data;
        Node next;
    }
}
