package com.oneces.tool.basic;

import java.util.NoSuchElementException;

/**
 * 实现单向链表集合
 */
public class LinkedList implements List {

    private Node head;
    private int size = 0;

    //检查是否越界 利用jdk源码的检测方法
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    //获取对应下标的节点
    Node node(int index) {
        Node x = head;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    }

    public void add(Object o) {

        if (head==null)
            head = new Node(o, null);
        else {
            final Node lastNode = node(size - 1);
            final Node newNode = new Node(o, null);
            lastNode.next = newNode;
        }
        size++;
    }

    public void add(int index, Object o) {
        checkPositionIndex(index);
        if (size == index)
            add(o);
        else {
            final Node prevNode = node(index - 1);
            final Node nextNode = prevNode.next;
            final Node newNode = new Node(o, nextNode);
            prevNode.next = newNode;
            size++;
        }
    }

    public Object get(int index) {
        return node(index);
    }

    public Object remove(int index) {
        checkElementIndex(index);
        final Node prevNode = node(index - 1);
        final Node x = prevNode.next;
        if (index-1<0){
            prevNode.next=null;
            head=x;
        }else {
            final Node nextNode = x.next;
            prevNode.next = nextNode;
            x.next = null;
        }
        size--;
        return x.data;
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        final Node h = head;
        final Node newNode = new Node(o, h);
        head = newNode;
        size++;
    }

    public void addLast(Object o) {
        add(o);
    }

    public Object removeFirst() {
        final Node h = head;
        if (h == null)
            throw new NoSuchElementException();
        final Node newFirst = h.next;
        h.next = null;
        head = newFirst;
        size--;
        return h.data;
    }

    public Object removeLast() {
        final Node prev = node(size - 1-1);
        final Node l = prev.next;
        prev.next = null;
        l.next = null;
        size--;
        return l.data;
    }

    public Iterator iterator() {
        return new LinkedItr();
    }
    private class LinkedItr implements Iterator{
        int cursor;//游标
        @Override
        public boolean hasNext() {
            return cursor!=size;
        }

        @Override
        public Object next() {
            int i=cursor;
            if (i>size-1)throw new NoSuchElementException();
            Node current = node(i);
            if (current==null)throw new IndexOutOfBoundsException();
            cursor=i+1;
            return current.data;
        }
    }


    private static class Node {
        Object data;
        Node next;

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < size; i++) {
            Node n = node(i);
            if (i == 0)
                result += n.data;
            else
                result += "," + n.data;

        }

        return result + "]";
    }
}
