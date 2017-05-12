package com.ifengdzh.code2017.basic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yuxia on 2017/4/23.
 */
public class LinkedListTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void add() throws Exception {
        List list = new LinkedList();
        assertEquals(true, list.isEmpty());
        list.add(1);
        list.add(1, 2);
        assertEquals(2, list.get(1));
    }

    @Test
    public void add1() throws Exception {
    }

    @Test
    public void remove() throws Exception {
    }

    @Test
    public void isEmpty() throws Exception {
    }

    @Test
    public void size() throws Exception {
    }

    @Test
    public void get() throws Exception {
    }

    @Test
    public void iterator() throws Exception {
    }

}