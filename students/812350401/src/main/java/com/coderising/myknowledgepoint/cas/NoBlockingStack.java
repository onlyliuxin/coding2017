package com.coderising.myknowledgepoint.cas;

import java.util.concurrent.atomic.AtomicReference;

public class NoBlockingStack<E> {
	static class Node<E> {
        final E item;
        Node<E> next;
        public Node(E item) { this.item = item; }
    }
  
    AtomicReference<Node<E>> head = new AtomicReference<Node<E>>();
    
    public void push(E item) {
        Node<E> newHead = new Node<E>(item);
        Node<E> oldHead;
        do {
            oldHead = head.get();
            newHead.next = oldHead;
        } while (!head.compareAndSet(oldHead, newHead));
    }
    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = head.get();
            if (oldHead == null) 
                return null;
            newHead = oldHead.next;
        } while (!head.compareAndSet(oldHead,newHead));
        return oldHead.item;
    }
    
}
