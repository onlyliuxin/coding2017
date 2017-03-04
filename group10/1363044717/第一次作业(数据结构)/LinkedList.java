package com.coding.basic;

import jdk.nashorn.internal.ir.ReturnNode;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {

    private Node head;//链表的头
    private Node tail;//链表的结尾
    private int size;//记录当前元素的size

    public void add(E e) {
        Node node = new Node(e);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        tail.next = null;
        size++;
    }

    private void checkIndexRange(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void add(int index, E e) {
        checkIndexRange(index);
        Node node = new Node(e);
        Node temp = head;
        Node temp2 = null;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        temp2 = temp.next;
        temp.next = node;
        node.next = temp2;
        size++;
    }

    @Override
    public E get(int index) {
        checkIndexRange(index);
        Node temp = head;

        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return (E) temp.data;
    }

    @Override
    public E remove(int index) {
        checkIndexRange(index);
        if (index == 0) {
            E e = removeFirst();
            return e;
        }
        if (index == size) {
            E e = removeLast();
            return e;
        }
        Node temp = head;
        Node temp2 = null;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        E e = (E) temp.next.data;
        temp2 = temp.next.next;
        temp.next = null;
        temp.next = temp2;
        size--;
        return e;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e) {
        Node node = new Node(e);
        node.next = head;
        head = node;
        size++;
    }

    public void addLast(E e) {
        this.add(e);
    }

    public E removeFirst() {
        E e = (E) head.data;
        head = head.next;
        size--;
        return e;
    }

    public E removeLast() {
        Node temp = head;
        for (int i = 0; i < size - 1; i++) {
            temp = temp.next;
        }
        temp.next = null;
        E e = (E) tail.data;
        tail = temp;
        size--;
        return e;
    }

    public Iterator iterator() {
        return new LinkedListIterator(this);
    }

    private static class Node<E> {
        E data;
        Node next;
        public Node(E e) {
            this.data = e;
        }
    }
    private class LinkedListIterator<E> implements Iterator{

        private Node head;//链表的头
        private Node tail;//链表的结尾
        private Node node;//当前遍历的node
        private int index;
        private int endIndex;

        public LinkedListIterator(LinkedList list){
            this.head=list.head;
            this.tail=list.tail;
            this.endIndex = list.size();
            node=head;
        }
        @Override
        public boolean hasNext() {
            return this.index < this.endIndex;
        }

        @Override
        public E next() {
            if(!this.hasNext()) {
                throw new NoSuchElementException();//没有元素了
            } else {
                if(index == 0){
                    node = head;
                }else {
                    node = node.next;
                }
                index++;
                return (E)node.data;
            }
        }
    }
}
