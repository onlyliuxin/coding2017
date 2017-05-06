package com.johnChnia.coding2017.basic.queue;

import com.johnChnia.coding2017.basic.ArrayList;

import java.util.NoSuchElementException;

/**
 * Created by john on 2017/3/10.
 * @// TODO: 2017/4/1 实现Iterator
 */
public class Queue<E> {

    private ArrayList<E> arrayList;

    /**
     * Constructs an queue using 10 capacity of ArrayList.
     */
    public Queue() {
        arrayList = new ArrayList<>();
    }


    /**
     * Inserts the specified element into this queue.returning
     * {@code true} upon success.
     * if no space is currently available.
     *
     * @param element the element to add
     * @return {@code true}
     */
    public boolean add(E element) {
        arrayList.add(element);
        return true;
    }

    /**
     * Retrieves and removes the head of this queue,throws an exception
     * if this queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public E remove() {
        if (arrayList.empty())
            throw new NoSuchElementException(emptyMsg());
        return arrayList.remove(0);

    }


    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code 0} if this queue is empty
     */
    public Object peek() {
        if (arrayList.empty())
            return 0;
        return arrayList.get(0);
    }


    public String toString() {
        return arrayList.toString();
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in this queue.
     */
    public int size() {
        return arrayList.size();
    }

    /**
     * Constructs an NoSuchElementException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     */
    private String emptyMsg() {
        return "Size: " + size();
    }

    public boolean empty() {
        return arrayList.size() == 0;
    }
}
