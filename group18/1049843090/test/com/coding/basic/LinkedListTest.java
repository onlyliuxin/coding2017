package com.coding.basic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * LinkedList Test
 */
public class LinkedListTest {

    LinkedList<String> linkedList;

    @Before
    public void setUp() throws Exception {
        linkedList = new LinkedList<>();
    }

    @After
    public void tearDown() throws Exception {
        linkedList = null;
    }

    @Test
    public void add() throws Exception {
        linkedList.add("first");
        linkedList.add("second");
        assertEquals(2, linkedList.size());

    }

    @Test
    public void add1() throws Exception {
        linkedList.add(0, "first");
        linkedList.add(1, "second");
        assertEquals("first", linkedList.get(0));
        assertEquals("second", linkedList.get(1));
        assertEquals(2, linkedList.size());
    }

    @Test
    public void get() throws Exception {
        linkedList.add(0, "first");
        linkedList.add(1, "second");
        linkedList.add("third");
        assertEquals("first", linkedList.get(0));
        assertEquals("second", linkedList.get(1));
        assertEquals("third", linkedList.get(2));
    }

    @Test
    public void remove() throws Exception {
        linkedList.add(0, "first");
        linkedList.add(1, "second");
        linkedList.add("third");
        linkedList.add("fourth");
        assertEquals("first", linkedList.remove(0));
        assertEquals("third", linkedList.remove(1));
        assertEquals("fourth", linkedList.remove(1));
        assertEquals(1, linkedList.size());

    }

    @Test
    public void size() throws Exception {
        linkedList.add(0, "first");
        linkedList.add(1, "second");
        linkedList.add("third");
        linkedList.add("fourth");
        assertEquals(4, linkedList.size());
    }

    @Test
    public void addFirst() throws Exception {
        linkedList.add("first");
        linkedList.add("second");
        linkedList.addFirst("first first");
        assertEquals("first first", linkedList.get(0));

    }

    @Test
    public void addLast() throws Exception {
        linkedList.add("first");
        linkedList.add("second");
        linkedList.addLast("last");
        assertEquals("last", linkedList.get(2));
    }

    @Test
    public void removeFirst() throws Exception {
        linkedList.add("first");
        linkedList.add("second");
        linkedList.add("third");
        assertEquals("first", linkedList.removeFirst());
        assertEquals("second", linkedList.removeFirst());
        assertEquals(1, linkedList.size());
        assertEquals("third", linkedList.get(0));
        assertEquals("third", linkedList.removeFirst());
        assertEquals(0, linkedList.size());

    }

    @Test
    public void removeLast() throws Exception {
        linkedList.add("first");
        linkedList.add("second");
        linkedList.add("third");
        assertEquals("third", linkedList.removeLast());
        assertEquals("second", linkedList.removeLast());
        assertEquals("first", linkedList.removeLast());
        assertEquals(0, linkedList.size());

    }

    @Test
    public void iterator() throws Exception {
        Iterator iterator = linkedList.iterator();
        assertEquals(false,iterator.hasNext());
        linkedList.add("A");
        assertEquals(true,iterator.hasNext());
        assertEquals("A",iterator.next());
        iterator.remove();
        assertEquals(0,linkedList.size());
    }

}