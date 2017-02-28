package zavier.week01.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {

    private Node head;

    private int size = 0;

    @Override
    public void add(Object o) {
        if (head == null) {
            head = new Node(o);
        } else {
            Node tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            Node node = new Node(o);

            tail.next = node;
        }
        size++;
    }

    @Override
    public void add(int index, Object o) {
        rangeCheckForAdd(index);
        if (index == 0) {
            Node node = new Node(o);
            node.next = head;
            head = node;
        } else {
            Node preDest = head;
            for (int i = 0; i < index - 1; i++) {
                preDest = preDest.next;
            }
            Node node = new Node(o);
            node.next = preDest.next;
            preDest.next = node;
        }

        size++;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Object get(int index) {
        rangeCheck(index);

        Node dest = head;
        for (int i = 0; i < index; i++) {
            dest = dest.next;
        }
        return dest.data;
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Object remove(int index) {
        rangeCheck(index);

        Node preDest = head;
        for (int i = 0; i < index - 1; i++) {
            preDest = preDest.next;
        }
        Node dest = preDest.next;
        preDest.next = dest.next;

        size--;
        return dest.data;
    }

    @Override
    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        Node node = new Node(o);
        node.next = head;
        head = node;
        size++;
    }

    public void addLast(Object o) {
        Node lastNode = head;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }

        Node node = new Node(o);
        lastNode.next = node;
        size++;
    }

    public Object removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node target = head;
        head = head.next;
        size--;
        return target.data;
    }

    public Object removeLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        Node preDest = head;
        while (preDest.next.next != null) {
            preDest = preDest.next;
        }
        Node dest = preDest.next;
        preDest.next = null;

        size--;
        return dest.data;
    }

    public Iterator iterator() {
        return null;
    }


    private static class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
            next = null;
        }
    }
}
