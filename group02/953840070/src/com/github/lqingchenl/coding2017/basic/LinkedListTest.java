package com.github.lqingchenl.coding2017.basic;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LinkedListTest {

    private static LinkedList testLinkedList = new LinkedList();

    /**
     * Method: add(Object o)
     */
    @Test
    public void testAdd() throws Exception {
        testLinkedList.add(1);
        testLinkedList.add(2);
        assertEquals(1, testLinkedList.get(0));
        assertEquals(2, testLinkedList.get(1));
    }

    /**
     * Method: add(int index, Object o)
     */
    @Test
    public void testAddForIndex() throws Exception {
        testLinkedList.add(0, 0);
        testLinkedList.add(1, 1);
        testLinkedList.add(2, 2);
        assertEquals(0, testLinkedList.get(0));
        assertEquals(1, testLinkedList.get(1));
        assertEquals(2, testLinkedList.get(2));
    }

    /**
     * Method: get(int index)
     */
    @Test
    public void testGet() throws Exception {
        testLinkedList.add(1);
        testLinkedList.add(2);
        assertEquals(1, testLinkedList.get(0));
        assertEquals(2, testLinkedList.get(1));
    }

    /**
     * Method: getNode(int index)
     */
    @Test
    public void testGetNode() throws Exception {
        testLinkedList.add(1);
        testLinkedList.add(2);
        assertNotNull(testLinkedList.get(0));
    }

    /**
     * Method: remove(int index)
     */
    @Test
    public void testRemove() throws Exception {
        testLinkedList.add(1);
        testLinkedList.add(2);
        assertEquals(1, testLinkedList.get(0));
    }

    /**
     * Method: size()
     */
    @Test
    public void testSize() throws Exception {
        testLinkedList.add(1);
        testLinkedList.add(2);
        assertEquals(2, testLinkedList.size());
    }

    /**
     * Method: addFirst(Object o)
     */
    @Test
    public void testAddFirst() throws Exception {
        testLinkedList.addFirst(1);
        assertEquals(1, testLinkedList.get(0));
    }

    /**
     * Method: addLast(Object o)
     */
    @Test
    public void testAddLast() throws Exception {
        testLinkedList.addLast(1);
        assertEquals(1, testLinkedList.get(0));
    }

    /**
     * Method: removeFirst()
     */
    @Test
    public void testRemoveFirst() throws Exception {
        testLinkedList.addFirst(1);
        testLinkedList.addFirst(2);
        assertEquals(2, testLinkedList.removeFirst());
        assertEquals(1, testLinkedList.removeFirst());
    }

    /**
     * Method: removeLast()
     */
    @Test
    public void testRemoveLast() throws Exception {
        testLinkedList.addFirst(1);
        assertEquals(1, testLinkedList.removeLast());
    }

    /**
     * Method: iterator()
     */
    @Test
    public void testIterator() throws Exception {
    }


} 
