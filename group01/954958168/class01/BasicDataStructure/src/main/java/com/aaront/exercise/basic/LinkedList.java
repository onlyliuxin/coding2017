package com.aaront.exercise.basic;

public class LinkedList implements List {

    private Node head = new Node(null);
    private int size = 0;

    public void add(Object o) {
        Node newNode = new Node(o);
        Node first = head.next;
        Node second = head;
        while (first != null) {
            second = first;
            first = first.next;
        }
        second.next = newNode;
        size++;
    }

    public void add(int index, Object o) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("索引超出范围");
        Node first = head;
        int i = 0;
        while (i < index) {
            first = first.next;
            i++;
        }
        Node node = new Node(o);
        node.next = first.next;
        first.next = node;
        size++;
    }

    public Object get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("索引超出范围");
        Node first = head.next;
        int i = 0;
        while (i < index) {
            first = first.next;
            i++;
        }
        return first.data;
    }

    public Object remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("索引超出范围");
        Node first = head;
        int i = 0;
        while (i < index) {
            first = first.next;
            i++;
        }
        Node element = first.next;
        first.next = first.next.next;
        size--;
        return element.data;
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        add(0, o);
    }

    public void addLast(Object o) {
        add(size, o);
    }

    public Object removeFirst() {
        return remove(0);
    }

    public Object removeLast() {
        return remove(size - 1);
    }

    public Iterator iterator() {
        return new LinkedListIterator(this);
    }

    public Object[] toArray() {
        Object[] objects = new Object[size];
        Node first = head.next;
        int pos = 0;
        while (first!= null) {
            objects[pos++] = first.data;
            first = first.next;
        }
        return objects;
    }

    private static class LinkedListIterator implements Iterator {

        private int pos = 0;
        private LinkedList linkedList;

        private LinkedListIterator(LinkedList linkList) {
            this.linkedList = linkList;
        }

        @Override
        public boolean hasNext() {
            return pos < linkedList.size();
        }

        @Override
        public Object next() {
            return linkedList.get(pos++);
        }

        @Override
        public void remove() {
            linkedList.remove(pos - 1);
            pos--;
        }
    }


    private static class Node {
        private Object data;
        private Node next;

        private Node(Object data) {
            this.data = data;
        }
    }
}
