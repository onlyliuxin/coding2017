package com.byhieg.coding2017;

import javax.swing.text.html.HTMLDocument;

public class LinkedList implements List {

    private Node head;
    int size = 0;

    public void add(Object o) {
        addLast(o);
    }

    public void add(int index, Object o) {
        checkRangeForAdd(index);
        if (index == size) {
            addLast(o);
        }
        Node nextNode = node(index);
        Node newNode = new Node(o, nextNode);

        Node prevNode;
        if (index == 0) {
            prevNode = null;
        } else {
            prevNode = node(index);
        }


        if (prevNode == null) {
            head = newNode;
        }else{
            prevNode.next = newNode;
        }

        size++;
    }


    private Node node(int index) {
        Node cursor = head;
        for (int i = 0; i < index; i++) {
            cursor = cursor.next;
        }
        return cursor;
    }

    private void checkRangeForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("指定的index超过界限");
        }
    }

    public Object get(int index) {
        checkRange(index);
        return node(index).data;
    }

    private void checkRange(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("指定index超过界限");
        }
    }

    public Object remove(int index) {
        checkRange(index);
        Node targetNode = node(index);
        Object o = targetNode.data;
        Node prevNode ;
        Node nextNode = targetNode.next;

        if (index == 0) {
            prevNode = null;
        }else{
            prevNode = node(index - 1);
        }
        if (prevNode == null) {
            head = nextNode;
            targetNode.next = null;
        }else {
            prevNode.next = nextNode;
            targetNode.next = null;
        }

        targetNode.data = null;
        size --;
        return o;
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        Node nextNode = head;
        Node newNode = new Node(o, nextNode);
        head = newNode;
        size++;
    }

    public void addLast(Object o) {
        Node newNode = new Node(o, null);
        if (size == 0) {
            head = newNode;
        }else{
            Node lastNode = node(size - 1);
            lastNode.next = newNode;
        }
        size++;
    }

    public Object removeFirst() {
        return remove(0);
    }

    public Object removeLast() {
        return remove(size() - 1);
    }

    public Iterator iterator() {

        return new MyIterator();
    }

    private class MyIterator implements Iterator {

        public Node cursor = head;
        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public Object next() {
            Object o = cursor.data;
            cursor = cursor.next;
            return o;
        }
    }



    private static class Node {
        Object data;
        Node next;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
