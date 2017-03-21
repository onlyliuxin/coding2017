package com.johnChnia.coding2017.basic;

import java.util.NoSuchElementException;

/**
 * Created by john on 2017/3/9.
 *
 * @// TODO: 2017/3/15 支持泛型
 */

public class LinkedList {

    private Node first = null;
    private int size = 0;

    /**
     * Constructs an empty list.
     */
    public LinkedList() {

    }

    private static class Node {
        int element;
        Node next;
        Node prev;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element element to be appended to this list
     */
    public void add(int element) {
        Node newNode = new Node();
        if (first == null) {
            addWhenListIsEmpty(newNode, element);
            return;
        }
        Node last = first;
        while (last.next != null)
            last = last.next;
        last.next = newNode;
        newNode.prev = last;
        newNode.next = null;
        newNode.element = element;
        size++;
    }

    private void addWhenListIsEmpty(Node newNode, int element) {
        first = newNode;
        first.element = element;
        first.next = null;
        first.prev = null;
        size++;
    }

    /**
     * Inserts the specified element at the beginning of this list.
     *
     * @param element the element to add
     */
    public void addFirst(int element) {
        Node newNode = new Node();
        if (first == null) {
            addWhenListIsEmpty(newNode, element);
            return;
        }
        newNode.next = first;
        newNode.prev = null;
        newNode.element = element;

        first.prev = newNode;
        first = newNode;
        size++;
    }


    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any
     * subsequent elements to the right (adds one to their indices).
     *
     * @param index   index at which the specified element is to be inserted.
     * @param element element to be inserted.
     * @throws RuntimeException if list size less than 2.
     */
    public void add(int index, int element) {
        if (size() < 2)
            throw new RuntimeException("list size should greater than or equal to 2");
        isElementIndex(index);
        if (index == 0) {
            addFirst(element);
            return;
        } else {
            Node temp = new Node();
            Node temp2 = first;
            for (int i = 0; i < index; i++) {
                temp2 = temp2.next;
            }
            temp2.prev.next = temp;
            temp.prev = temp2.prev;

            temp.next = temp2;
            temp2.prev = temp;
            temp.element = element;
        }
        size++;

    }


    /**
     * remove last element in the list.
     *
     * @throws RuntimeException if the list is empty.
     */
    public void remove() {
        if (size == 0)
            throw new RuntimeException("linkList size should greater than or equal to 1");
        Node next = first.next;
        if (next == null) {
            first = null;
        } else {
            Node last = first;
            while (last.next != null)
                last = last.next;
            last.prev.next = null;
            last = null;  // help GC
        }
        size--;
    }


    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     */
    public int removeFirst() {
        Node f = first;
        if (f == null)
            throw new NoSuchElementException();
        int element = f.element;
        Node next = first.next;
        first.element = 0;
        first.next = null; // help GC

        first = next;
        if (next != null) {
            next.prev = null;
        }
        size--;
        return element;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    public int get(int index) {
        checkElementIndex(index);
        Node node = first;
        if (index == 0) {
            return first.element;
        }
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.element;
    }

    /**
     * Returns the first element in this list.
     *
     * @return the first element in this list
     * @throws NoSuchElementException if this list is empty
     */
    public int getFirst() {
        final Node f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.element;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }


    /**
     * Tells if the argument is the index of an existing element.
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(first.element);
        Node temp = first;
        while (temp.next != null) {
            temp = temp.next;
            sb.append("→");
            sb.append(temp.element);
        }
        return sb.toString();
    }

    /**
     * Constructs an IndexOutOfBoundsException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }
}
