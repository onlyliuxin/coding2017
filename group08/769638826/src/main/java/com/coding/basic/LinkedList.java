package com.coding.basic;

import java.util.NoSuchElementException;

/**
 * Created by huitailang on 17/2/25.
 *
 * @author zhangkun
 * @date 2017年02月25日13:57:58
 */
public class LinkedList implements List {
    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public void add(Object o) {
        if (head == null) {
            Node newNode = new Node();
            newNode.data = o;
            head = newNode;
        }

        Node oldhead = head;
        head = new Node();
        head.data = o;
        head.next = oldhead;
        size++;
    }

    @Override
    public void add(int index, Object o) {
        Node newNode = new Node();
        newNode.data = o;

        if (head == null) {
            head = newNode;
        }

        if (index < 1 || index > size + 1) {
            throw new IllegalArgumentException("invalid index, it's should be 1 and" + size + 1);
        }

        if (index == 1) {
            newNode.next = head;
        } else {
            Node currentNode = head;
            int count = 1;
            while (count < index - 1) {
                count++;
                currentNode = currentNode.next;
            }
            newNode.next = currentNode.next;
            currentNode.next = newNode;
        }
    }

    @Override
    public Object get(int index) {
        if (head == null) {
            return null;
        }

        if (index == 1) {
            return head.next.data;
        } else {
            Node currentNode = head;
            int count = 1;
            while (count < index - 1) {
                count++;
                currentNode = currentNode.next;
            }

            return currentNode.next.data;
        }
    }

    @Override
    public Object remove(int index) {
        Object result = null;

        if (index < 1 || index > size) {
            throw new IllegalArgumentException("invalid index, it's should be 1 and " + size);
        }

        if (index == 1) {
            Node currentNode = head.next;
            head = null;
            return currentNode;
        } else {
            Node prevNode = head;

            int count = 1;
            while (count < index - 1) {
                prevNode = prevNode.next;
                count++;
            }
            Node currentNode = prevNode.next;
            prevNode.next = currentNode.next;
            result = currentNode.data;
            currentNode = null;
        }

        return result;
    }

    @Override
    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        add(1, o);
    }

    public void addLast(Object o) {
        add(size + 1, o);
    }

    public Object removeFirst() {
        return remove(1);
    }

    public Object removeLast() {
        return remove(size);
    }

    public Iterator iterator() {
        return new ListIterator();
    }

    public void print() {
        if (head == null) {
            System.out.println("No elements in the list!");
        }

        Node currentNode = head;
        while (currentNode != null) {
            System.out.println(currentNode.data + "->");
            currentNode = currentNode.next;
        }

        System.out.println();
    }

    public int length() {
        int count = 0;

        Node currentNode = head;
        while (currentNode != null) {
            count++;
            currentNode = currentNode.next;
        }

        return count;
    }

    private static class Node {
        Object data;
        Node next;
    }

    private class ListIterator implements Iterator {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Object o = current.data;
            current = current.next;
            return o;
        }
    }
}
