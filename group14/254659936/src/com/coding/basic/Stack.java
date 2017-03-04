package com.coding.basic;

public class Stack {

    private Node mStackNode;
    private int size;

    public void push(Object o) {
        Node node = new Node();
        node.data = o;
        if (null == mStackNode) {
            mStackNode = node;
        } else {
            mStackNode.next = node;
            mStackNode = node;
        }
        size++;
    }

    public Object pop() {
        if (size == 0) {
            throw new RuntimeException("the stack is empty");
        }
        Object obj = mStackNode.data;
        mStackNode = mStackNode.pre;
        size--;
        return obj;
    }

    public Object peek() {
        if (size == 0) {
            throw new RuntimeException("the stack is empty");
        }
        return mStackNode.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private static class Node {
        Object data;
        Node next;
        Node pre;
    }
}
