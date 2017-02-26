package com.coding.basic;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayListTest {

    @Test
    public void add() throws Exception {
        ArrayList data = new ArrayList();
        int size = 356;
        for (int i = 0; i < size; i++) {
            data.add(i);
        }
        for (int i = 0; i < size; i++) {
            assertEquals(i, data.get(i));
        }
    }

    @Test
    public void add1() throws Exception {

    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void remove() throws Exception {

    }

    @Test
    public void size() throws Exception {
        ArrayList data = new ArrayList();
        data.add(1);
        data.add(1);
        data.add(1);
        assertEquals(3, data.size());
    }

    @Test
    public void iterator() throws Exception {

    }

}