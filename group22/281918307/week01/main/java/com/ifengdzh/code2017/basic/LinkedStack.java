package com.ifengdzh.code2017.basic;

/**
 * 链表指针:先进后出
 * Created by ajaxfeng on 2017/3/12.
 */
public class LinkedStack implements Stack {

    private Node head;
    private Node tail;
    private int size = 0;

    public static class Node {
        public Object data;
        public Node next;
        public Node pre;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(Object data) {
            this(data, null);
        }
    }

    @Override
    public Object push(Object o) {
        Node node = new Node(o);
        if (head == null) {
            head = node;
            tail = head;
        } else {
            tail.next = node;
            node.pre = tail;
            tail = node;
        }
        size++;
        return o;
    }

    @Override
    public Object pull() {
        if (head == null) {
            return null;
        }
        Object data = tail.data;
        tail = tail.pre;
        size--;
        return data;
    }

    @Override
    public Object peek() {
        return tail.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
