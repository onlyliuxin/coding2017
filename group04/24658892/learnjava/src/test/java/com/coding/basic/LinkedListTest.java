package com.coding.basic;

import org.junit.Test;

public class LinkedListTest {

    @Test
    public void add() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        System.out.print(linkedList.toString());
    }

    @Test
    public void add1() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(2,21);
        System.out.print(linkedList.toString());
    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void remove() throws Exception {

    }

    @Test
    public void size() throws Exception {

    }

    @Test
    public void addFirst() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);
        System.out.print(linkedList.toString());
    }

    @Test
    public void addLast() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        System.out.print(linkedList.toString());
    }

    @Test
    public void removeFirst() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.removeFirst();
        linkedList.removeFirst();
        System.out.print(linkedList.toString());
    }

    @Test
    public void removeLast() throws Exception {

    }

    @Test
    public void iterator() throws Exception {

    }

}