package com.basic;

/**
 * @Description:
 * @author: {User}
 * @date: {Date}
 */
public class Stack<E> {

    private class Node {

        private E data; // 数据域

        private Node next;  // 指针域

        public Node() {
        }

        private Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private int size;

    private Node top;

    public void push(E e) {
        if(!isEmpty()) {
            Node newNode = new Node(e);
            newNode.next = top;
            top = newNode;
        } else {
            top = new Node(e);
        }
        size++;
    }

    public E pop() {
        if(isEmpty()) return null;
        Node popNode = top;
        top = top.next;
        size--;
        return popNode.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E peek() {
        if(isEmpty()) return null;
        return top.data;
    }

    public int size() {
        return size;
    }

    public void clear() {
        top = null;
        size = 0;
    }

    @Override
    public String toString() {

        if(isEmpty()) return "[]";
        StringBuilder stackToString = new StringBuilder("[");
        Node itr = this.top;
        while(itr != null) {
            stackToString.append(itr.data.toString()).append(",");
            itr = itr.next;
        }
        stackToString.deleteCharAt(stackToString.length() - 1).append("]");

        return stackToString.toString();
    }


}
