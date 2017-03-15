package com.johnChnia.coding2017.basic;

import java.util.EmptyStackException;

/**
 * Created by john on 2017/3/10.
 * @// TODO: 2017/3/15 支持泛型
 */
public class Stack {
    private LinkedList linkList;

    /**
     * Creates an empty Stack.
     */
    public Stack() {
        linkList = new LinkedList();
    }


    /**
     * Pushes an item onto the top of this stack.
     *
     * @param element the element to be pushed onto this stack.
     */
    public void push(int element) {
        linkList.addFirst(element);
    }

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     *
     * @return The object at the top of this stack.
     * @throws EmptyStackException if this stack is empty.
     */
    public int pop() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return linkList.removeFirst();
    }

    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return the object at the top of this stack.
     * @throws EmptyStackException if this stack is empty.
     */
    public int peek() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return linkList.getFirst();
    }

    /**
     * Tests if this stack is empty.
     *
     * @return <code>true</code> if and only if this stack contains
     *          no elements; <code>false</code> otherwise.
     */
    public boolean empty() {
        return linkList.size() == 0;
    }

    public String toString() {
        return linkList.toString();
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return the number of elements in this stack
     */
    public int size() {
        return linkList.size();
    }
}
