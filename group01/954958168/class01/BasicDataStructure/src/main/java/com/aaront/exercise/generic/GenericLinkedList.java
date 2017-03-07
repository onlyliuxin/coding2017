package com.aaront.exercise.generic;

import java.util.Arrays;

public class GenericLinkedList<T> implements GenericList<T> {

    private Node<T> head = new Node<>(null);
    private int size = 0;

    public void add(T o) {
        Node<T> newNode = new Node<>(o);
        Node<T> first = head.next;
        Node<T> second = head;
        while (first != null) {
            second = first;
            first = first.next;
        }
        second.next = newNode;
        size++;
    }

    public void add(int index, T o) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("索引超出范围");
        Node<T> first = head;
        int i = 0;
        while (i < index) {
            first = first.next;
            i++;
        }
        Node<T> node = new Node<>(o);
        node.next = first.next;
        first.next = node;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("索引超出范围");
        Node<T> first = head.next;
        int i = 0;
        while (i < index) {
            first = first.next;
            i++;
        }
        return first.data;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("索引超出范围");
        Node<T> first = head;
        int i = 0;
        while (i < index) {
            first = first.next;
            i++;
        }
        Node<T> element = first.next;
        first.next = first.next.next;
        size--;
        return element.data;
    }

    public int size() {
        return size;
    }

    public void addFirst(T o) {
        add(0, o);
    }

    public void addLast(T o) {
        add(size, o);
    }

    public T removeFirst() {
        return remove(0);
    }

    public T removeLast() {
        return remove(size - 1);
    }

    public GenericIterator<T> iterator() {
        return new LinkedListGenericIterator<>(this);
    }

    public Object[] toArray() {
        Object[] objects = new Object[size];
        Node<T> first = head.next;
        int pos = 0;
        while (first != null) {
            objects[pos++] = first.data;
            first = first.next;
        }
        return objects;
    }

    public T[] toArray(T[] a) {
        Object[] elementData = toArray();
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        return a;
    }

    private static class LinkedListGenericIterator<T> implements GenericIterator<T> {

        private int pos = 0;
        private GenericLinkedList<T> genericLinkedList;

        private LinkedListGenericIterator(GenericLinkedList<T> linkList) {
            this.genericLinkedList = linkList;
        }

        @Override
        public boolean hasNext() {
            return pos < genericLinkedList.size();
        }

        @Override
        public T next() {
            return genericLinkedList.get(pos++);
        }

        @Override
        public void remove() {
            genericLinkedList.remove(pos - 1);
            pos--;
        }
    }


    private static class Node<T> {
        private T data;
        private Node<T> next;

        private Node(T data) {
            this.data = data;
        }
    }
}
