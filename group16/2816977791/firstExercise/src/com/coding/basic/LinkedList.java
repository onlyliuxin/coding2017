package com.coding.basic;

public class LinkedList implements List {

    private Node head;
    private Node last;

    private int size = 0;

    public void add(Object o) {
        addLast(o);
    }

    public void add(int index, Object o) {
        checkPosition(index);
        if (index == size) {
            addLast(o);
        } else {
            addIndex(index, o);
        }
    }

    public Object get(int index) {
        return node(index).data;
    }

    public Object remove(int index) {
        checkPosition(index);
        Object old = get(index);
        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            node(index - 1).next = node(index + 1);
            size--;
        }
        return old;
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        Node h = head;
        Node newNode = new Node(o, h);
        head = newNode;
        if (h == null) {
            last = newNode;
        }
        size++;
    }

    public void addLast(Object o) {
        Node l = last;
        Node newNode = new Node(o, null);
        last = newNode;
        if (l == null) {
            head = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public Object removeFirst() {
        Node old = head;
        head = old.next;
        size--;
        return old.data;
    }

    public Object removeLast() {
        Node old = last;
        Node prev = node(size - 2);
        last = prev;
        size--;
        return old.data;
    }

    public Iterator iterator() {
        return null;
    }

    private void checkPosition(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("out of memory");
        }
    }

    private void addIndex(int index, Object o) {
        checkPosition(index);
        Node newNode = new Node(o, node(index));
        if (index != 0) {
            node(index - 1).next = newNode;
        } else {
            head = newNode;
        }
        size++;
    }

    private Node node(int index) {
        Node x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    private static class Node {
        Object data;
        Node next;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addLast("last");
        System.out.println(list.size());
        list.addFirst("head");
        System.out.println(list.size());
        list.add(0, "0Object");
        System.out.println(list.size());
        list.add(2, "2Object");
        System.out.println(list.size());
        list.removeLast();
        System.out.println(list.size());
        list.removeFirst();
        System.out.println(list.size());
        list.removeLast();
        System.out.println(list.size());
        list.get(0);
        System.out.println(list.size());
    }
}
