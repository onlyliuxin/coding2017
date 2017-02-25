package com.coding.basic;

import java.util.Objects;

/**
 * Created by zhangjiatao on 2017/2/25.
 */
public class LinkedList implements List {
    private Node head = null,tail = null;
    private int size=0;
    private LinkedListIterator iterator = null;

    @Override
    public void add(Object o) {
        Node newNode = new Node(o);
        if(tail == null) {
            head = tail = newNode;
        }else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, Object o) {
        ifOutOfBounds(index);
        Node temp = head,previousNode = head;
        Node newNode = new Node(o);
        if(index == 0) {
            newNode.next = head;
            head = newNode;
            return ;
        }
        while(index-- > 0) {
            previousNode = temp;
            temp = temp.next;
        }
        previousNode.next = newNode;
        newNode.next = temp;
        size++;
    }

    @Override
    public Object get(int index) {
        ifOutOfBounds(index);
        Node temp = head;
        while(index-- > 0) {
            temp = temp.next;
        }
        return temp;
    }

    @Override
    public Object remove(int index) {
        ifOutOfBounds(index);
        Node temp = head,previousNode = head;
        if(head == tail) {
            head = tail = null;
            return temp;
        }
        while(index-- > 0) {
            previousNode = temp;
            temp = temp.next;
        }
        if(tail == temp) {
            tail = previousNode;
        }
        previousNode.next = temp.next;
        size--;
        return temp;
    }

    @Override
    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        add(0,o);
    }

    public void addLast(Object o) {
        add(o);
    }

    public Object removeFirst() {
        return remove(0);
    }

    public Object removeLast() {
        return remove(size()-1);
    }

    public LinkedListIterator Iterator() {
        if(iterator ==null) {
            iterator = new LinkedListIterator(this);
        }
        return iterator;
    }

    private void ifOutOfBounds(int index) {
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isEmpty() {
        return size==0;
    }

    public String toString() {
        String str = "";
        Node temp = head;
        while(temp!=tail) {
            str = str + temp.data + " ";
            temp = temp.next;
        }
        return str + tail.data;
    }

    private static class Node {
        Object data;
        Node next;
        public Node(Object o) {
            this.data = o;
            this.next = null;
        }
    }

    private class LinkedListIterator implements Iterator {
        private LinkedList linkedList = null;
        private Node currentNode = null;

        public LinkedListIterator(LinkedList linkedList) {
            this.linkedList = linkedList;
            this.currentNode = this.linkedList.head;
        }

        @Override
        public boolean hasNext() {
            if(linkedList.isEmpty()) return false;
            return currentNode.next != null;
        }

        @Override
        public Object next() {
            Object data = currentNode.data;
            if(hasNext()) {
                currentNode = currentNode.next;
            }
            return data;
        }
    }

    public static void main(String[] args) {
        LinkedList arr = new LinkedList();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(5);
        arr.add(1,4);
        arr.remove(2);
        arr.removeLast();
        arr.addFirst(5);
        System.out.println(arr.toString());
        System.out.println(arr.size());
        System.out.println(arr.Iterator().hasNext());
        System.out.println(arr.Iterator().next());
        System.out.println(arr.Iterator().next());
        System.out.println(arr.Iterator().next());
        System.out.println(arr.Iterator().next());
    }
}
