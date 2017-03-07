package com.coding.basic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ArrayList Test
 */
public class ArrayListTest {

    ArrayList<String> list;

    @Before
    public void setUp() throws Exception {
        list = new ArrayList<>();

    }

    @After
    public void tearDown() throws Exception {
        list = null;
    }

    @Test
    public void add() throws Exception {
        list.add("first");
        assertEquals("first", list.get(0));
    }

    @Test
    public void add1() throws Exception {
        list.add(0, "first");
        assertEquals("插入第一条", "first", list.get(0));
        list.add(0, "insert");
        assertEquals("插入第二条", "insert", list.get(0));
        list.add(2, "position_2");
        assertEquals("position_2", list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    public void get() throws Exception {
        list.add("first");
        list.add("second");
        list.add("third");
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
        assertEquals("third", list.get(2));

    }

    @Test
    public void remove() throws Exception {
        list.add("first");
        list.add("second");
        list.add("third");
        list.add("fourth");
        assertEquals("first", list.remove(0));
        assertEquals(3, list.size());
        assertEquals("third", list.remove(1));
        assertEquals("fourth", list.remove(1));
        assertEquals(1, list.size());

    }

    @Test
    public void size() throws Exception {
        list.add("first");
        assertEquals(1,list.size());
        list.add("second");
        assertEquals( 2,list.size());
    }


    @Test
    public void iterator() throws Exception {
        Iterator iterator = list.iterator();
        assertEquals(false,iterator.hasNext());
        list.add("A");
        assertEquals(true,iterator.hasNext());
        assertEquals("A",iterator.next());
        iterator.remove();
        assertEquals(0,list.size());
    }

}