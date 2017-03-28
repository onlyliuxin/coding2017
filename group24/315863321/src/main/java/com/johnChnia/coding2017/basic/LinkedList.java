package com.johnChnia.coding2017.basic;

import java.util.NoSuchElementException;

/**
 * Created by john on 2017/3/9.
 *
 * @// TODO: 2017/4/1 支持Iterator
 */

public class LinkedList<E> implements List<E> {

    private Node<E> first = null;
    private int size = 0;

    /**
     * Constructs an empty list.
     */
    public LinkedList() {

    }

    private static class Node<T> {
        T element;
        Node<T> next;
        Node<T> prev;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element element to be appended to this list
     */
    public void add(E element) {
        Node<E> newNode = new Node<>();
        if (first == null) {
            addWhenListIsEmpty(newNode, element);
            return;
        }
        Node<E> last = first;
        while (last.next != null)
            last = last.next;
        last.next = newNode;
        newNode.prev = last;
        newNode.next = null;
        newNode.element = element;
        size++;
    }

    private void addWhenListIsEmpty(Node<E> newNode, E element) {
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
    public void addFirst(E element) {
        Node<E> newNode = new Node<>();
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
    public void add(int index, E element) {
        if (size() < 2)
            throw new RuntimeException("list size should greater than or equal to 2");
        isElementIndex(index);
        if (index == 0) {
            addFirst(element);
            return;
        } else {
            Node<E> temp = new Node<>();
            Node<E> temp2 = first;
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
        Node<E> next = first.next;
        if (next == null) {
            first = null;
        } else {
            Node<E> last = first;
            while (last.next != null)
                last = last.next;
            last.prev.next = null;
            last = null;  // help GC
        }
        size--;
    }


    /**
     * @param index
     * @return
     * @// TODO: 2018/3/14 if i am happy, i will implement it right now!
     */
    public E remove(int index) {
        return null;
    }

    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     */
    public E removeFirst() {
        Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        E element = f.element;
        Node<E> next = first.next;
        first.element = null;
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
    public E get(int index) {
        checkElementIndex(index);
        Node<E> node = first;
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
    public E getFirst() {
        final Node<E> f = first;
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
        Node<E> temp = first;
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
