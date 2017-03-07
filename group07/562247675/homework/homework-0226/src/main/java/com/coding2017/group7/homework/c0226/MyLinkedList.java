package com.coding2017.group7.homework.c0226;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyLinkedList implements MyList {

    private Node head = new Node();
    private int size = 0;

    @Override
    public void add(Object o) {
        Node node = new Node();
        node.data = o;
        Node last = find(size - 1);
        last.next = node;
        size++;
    }

    @Override
    public void add(int index, Object o) {
        checkRangeAdd(index);
        Node insert = find(index);
        Node before = find(index - 1);
        Node node = new Node();
        node.data = o;
        node.next = insert;
        before.next = node;
        size++;
    }

    @Override
    public Object get(int index) {
        checkRangeGet(index);
        return find(index).data;
    }

    @Override
    public Object remove(int index) {
        checkRangeGet(index);
        Node before = find(index - 1);
        Node remove = find(index);
        Node after = find(index + 1);
        before.next = after;
        size--;
        return remove.data;
    }

    @Override
    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        Node node = new Node();
        node.data = o;
        node.next = find(0);
        head.next = node;
        size++;
    }

    public void addLast(Object o) {
        Node last = find(size - 1);
        Node node = new Node();
        node.data = o;
        last.next = node;
        size++;
    }

    public Object removeFirst() {
        checkRangeGet(size - 1);
        Node remove = find(0);
        head.next = find(1);
        size--;
        return remove.data;
    }

    public Object removeLast() {
        checkRangeGet(size - 1);
        Node remove = find(size - 1);
        Node before = find(size - 2);
        before.next = null;
        size--;
        return remove.data;
    }

    public MyIterator iterator() {
        return new MyLinkedListIterator();
    }

    private void checkRangeGet(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size:" + size);
        }
    }

    private void checkRangeAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size:" + size);
        }
    }

    private Node find(int index) {
        Node node = head;
        int pos = -1;
        while (pos < index) {
            node = node.next;
            pos++;
        }
        return node;
    }

    @Override
    public String toString() {
        Node node = head;
        StringBuilder sBuilder = new StringBuilder();
        while (node.next != null) {
            node = node.next;
            sBuilder.append(node.data).append(", ");
        }
        int length = sBuilder.length();
        if (length > 0) {
            sBuilder.delete(length - 2, length);
        }
        return "[" + sBuilder.toString() + "]";
    }

    private static class Node {
        Object data;
        Node next;

    }


    private class MyLinkedListIterator implements MyIterator {


        private Node node = head;
        private int size = 0;

        @Override
        public boolean hasNext() {
            return node.next != null;
        }

        @Override
        public Object next() {
            checkRangeGet(size);
            node = node.next;
            size++;
            return node.data;
        }
    }
}
