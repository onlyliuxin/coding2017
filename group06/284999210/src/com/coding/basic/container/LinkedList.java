/**
 *
 */
package com.coding.basic.container;

/**
 * @author Administrator
 *
 */
public class LinkedList implements List {

    private int size;
    private Node head;

    public LinkedList() {
        head = new Node(null, null, null);
        size = 0;
    }

    @Override
    public boolean add(Object o) {
        Node nodeAdd;
        Node nodeCurrent = head;
        while (nodeCurrent.next != null) {
            nodeCurrent = nodeCurrent.next;
        }
        nodeAdd = new Node(o, nodeCurrent, null);
        nodeCurrent.next = nodeAdd;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (head.next == null) {
            return false;
        }
        Node nodeCurrent = head;

        while (nodeCurrent.next != null) {
            nodeCurrent = nodeCurrent.next;
            if (nodeCurrent.data.equals(o)) {
                nodeCurrent.previous.next = nodeCurrent.next;
                nodeCurrent.next.previous = nodeCurrent.previous;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public Object get(int index) {
        checkIndex(index);
        int i = 0;
        Node nodeCurrent = head;
        do {
            nodeCurrent = nodeCurrent.next;
        } while (i < index);
        return nodeCurrent.data;
    }

    @Override
    public Object set(int index, Object element) {
        checkIndex(index);
        int i = 0;
        Node nodeCurrent = head;
        do {
            nodeCurrent = nodeCurrent.next;
        } while (i < index);
        Object o = nodeCurrent.data;
        nodeCurrent.data = element;
        return o;
    }

    @Override
    public void add(int index, Object element) {
        checkIndex(index);
        int i = 0;
        Node nodeCurrent = head;
        do {
            nodeCurrent = nodeCurrent.next;
        } while (i < index);
        Node nodeNew = new Node(element, nodeCurrent.previous, nodeCurrent);
        nodeCurrent.previous = nodeNew;
        size++;
    }

    @Override
    public Object remove(int index) {
        checkIndex(index);
        Node nodeCurrent = head;

        int i = 0;
        do {
            nodeCurrent = nodeCurrent.next;
        } while (i < index);

        Object o = nodeCurrent.data;
        if (index == size - 1) {
            nodeCurrent.previous.next = null;
        } else {
            nodeCurrent.previous.next = nodeCurrent.next;
            nodeCurrent.next.previous = nodeCurrent.previous;
        }

        size--;
        return o;
    }

    private void checkIndex(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return 0 == size;
    }

    private static class Node {
        public Node(Object data, Node pre, Node next) {
            this.data = data;
            this.previous = pre;
            this.next = next;
        }

        Object data;
        Node previous;
        Node next;
    }

    public void addFirst(Object o) {
        add(0, o);
    }

    public void addLast(Object o) {
        add(o);
    }

    public Object removeFirst() {
        Object o = remove(0);
        return o;
    }

    public Object removeLast() {
        Object o = remove(size);
        return o;
    }

    @Override
    public Iterator iterator() {
        return new IteratorlinkedList();
    }

    private class IteratorlinkedList implements Iterator {

        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor < size - 1;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                int i = 0;
                Node nodeCurrent = head;
                do {
                    nodeCurrent = nodeCurrent.next;
                } while (i < cursor + 1);
                cursor++;
                return nodeCurrent.data;
            } else {
                return null;
            }
        }
    }
}
