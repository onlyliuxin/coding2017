package cn.net.pikachu.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {

    private Node head;
    private int curSize = 0;

    private class Itr implements Iterator {
        Node curNode = head;

        @Override
        public boolean hasNext() {
            return curNode != null;
        }

        @Override
        public Object next() {
            Node node = curNode;
            curNode = curNode.next;
            return node.data;
        }
    }

    public void add(Object o) {
        if (head == null) {
            head = new Node(o, null);
        } else {
            Node node = head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node(o, null);
        }
        curSize++;
    }

    public void add(int index, Object o) {
        // 这里可以等于
        if (index < 0 || index > curSize) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        if (index == 0) {
            addFirst(o);
        } else {

            Node node = head;
            for (int i = 1; i < index; i++) {
                node = node.next;
            }
            node.next = new Node(o, node.next);
            curSize++;
        }

    }

    public Object get(int index) {
        if (index < 0 || index >= curSize) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }

    public Object remove(int index) {
        if (index < 0 || index >= curSize) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        if (index == 0) {
            return removeFirst();
        }

        Node node = head;
        for (int i = 1; i < index; i++) {
            node = node.next;
        }
        Node t = node.next;
        node.next=t.next;
        curSize--;
        return t.data;
    }

    public int size() {
        return curSize;
    }

    public void addFirst(Object o) {
        Node node = new Node(o, head);
        head = node;
        curSize++;
    }

    public void addLast(Object o) {
        add(o);
    }

    public Object removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node node = head;
        head = head.next;
        curSize--;
        return node.data;
    }

    public Object removeLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node node;
        if (head.next == null) {
            node = head;
            head = null;
        } else {
            node = head;
            while (node.next.next != null) {
                node = node.next;
            }
            Node t = node.next;
            node.next = null;
            node = t;
        }
        curSize--;
        return node.data;
    }

    // 后面再实现
    public Iterator iterator() {
        return new Itr();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node node = head;
        while (node != null) {
            builder.append(node.data).append(",");
            node = node.next;
        }
        if (curSize>0)
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }

    private static class Node {
        Object data;
        Node next;

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
