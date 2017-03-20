package com.coding.basic.datastructure;

/**
 * Created by zt on 2017/2/19.
 */
public class LinkedList implements List {

    private Node head;

    private Node tail;

    private int size = 0;

    public LinkedList() {

    }

    @Override
    public void add(Object object) {
        if (null == head) {
            head = new Node(object);
            head.next = null;
            tail = head;
            size++;
        } else {
            // 尾插法
            Node newNode = new Node(object);
            tail.next = newNode;
            tail = newNode;
            tail.next = null;
            size++;
        }
    }

    @Override
    public void add(int index, Object object) {
        checkRange(index);
        if (null == head) {
            add(object);
            return;
        }
        if (index == 0) {
            addFirst(object);
            return;
        }
        Node pre = node(index - 1);
        Node newNode = new Node(object);
        newNode.next = pre.next;
        pre.next = newNode;
        size++;
    }

    @Override
    public Object get(int index) {
        checkRange(index);
        checkNodeNotNull();
        Node node = node(index);
        return node.data;
    }

    @Override
    public Object remove(int index) {
        checkRange(index);
        checkNodeNotNull();
        if (index == 0) {
            removeFirst();
            return head;
        }
        Node pre = node(index - 1);
        pre.next = pre.next.next;
        size--;
        return head;
    }

    @Override
    public int size() {
        return size;
    }

    public void addFirst(Object object) {
        if (null == head) {
            head = new Node(object);
            head.next = null;
            size++;
        } else {
            Node firstNode = new Node(object);
            firstNode.next = head;
            head = firstNode;
            size++;
        }
    }

    public Object removeFirst() {
        checkNodeNotNull();
        head = head.next;
        size--;
        return head;
    }

    public Object removeLast() {
        checkNodeNotNull();
        if (size == 1) {
            head = null;
        }
        Node pre = node(size() - 2);
        pre.next = null;
        size--;
        return head;
    }

    private void checkRange(int index) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkNodeNotNull() {
        if (null == head) {
            throw new NullPointerException();
        }
    }

    private Node node(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private static class Node {
        Node next;
        private Object data;

        public Node() {

        }

        public Node(Object data) {
            this.data = data;
        }

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(Object data, Node next, Node prev) {
            this.data = data;
            this.next = next;
        }
    }

     /*@Override
    public void add(Object object) {
        if (null == head) {
            head = new Node(object);
            head.next = null;
        } else {
            // 头插法
            Node nextNode = new Node(object);
            nextNode.next = head.next;
            head.next = nextNode;
        }
    }*/

}
