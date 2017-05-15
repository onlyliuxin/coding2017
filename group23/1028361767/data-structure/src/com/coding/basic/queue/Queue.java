package com.coding.basic.queue;

import java.util.NoSuchElementException;

public class Queue<E> {
    private Node<E> first;    
    private Node<E> last;     
    private int size;               

    
    private static class Node<E> {
        private E item;
        private Node<E> next;
    }

    
    public Queue() {
        first = null;
        last  = null;
        size = 0;
    }

    
    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    

    public void enQueue(E data) {
        Node<E> oldlast = last;
        last = new Node<E>();
        last.item = data;
        last.next = null;
        if (isEmpty()) {
        	first = last;
        }
        else{
        	oldlast.next = last;
        }
        size++;
    }

    public E deQueue() {
        if (isEmpty()) {
        	throw new NoSuchElementException("Queue underflow");
        }
        E item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
        	last = null;  
        }
        return item;
    }

}
