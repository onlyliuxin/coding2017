package com.johnChnia.coding2017.basic;

import java.util.NoSuchElementException;

/**
 * Created by john on 2017/3/10.
 * @// TODO: 2017/3/15 支持泛型 
 */
public class Queue {

    private ArrayList arrayList;

    /**
     * Constructs an queue using 10 capacity of ArrayList.
     */
    public Queue() {
        arrayList = new ArrayList(10);
    }


    /**
     * Inserts the specified element into this queue.returning
     * {@code true} upon success.
     * if no space is currently available.
     *
     * @param element the element to add
     * @return {@code true}
     */
    public boolean add(int element) {
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
    public int remove() {
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
    public int peek() {
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
}
