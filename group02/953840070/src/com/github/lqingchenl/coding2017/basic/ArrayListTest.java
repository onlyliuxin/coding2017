package com.github.lqingchenl.coding2017.basic;

import com.github.lqingchenl.coding2017.basic.ArrayList;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;

/**
 * ArrayList Tester.
 */
public class ArrayListTest {

    private static ArrayList testArray = new ArrayList();

    /**
     * Method: add(Object o)
     */
    @Test
    public void testAddO() throws Exception {
        testArray.add(1);
        testArray.add(2);
        assertEquals(1, testArray.get(0));
        assertEquals(2, testArray.get(1));
    }

    /**
     * Method: add(int index, Object o)
     */
    @Test
    public void testAddForIndexO() throws Exception {
        testArray.add(1, 1);
        testArray.add(2, 2);
        assertEquals(1, testArray.get(0));
        assertEquals(2, testArray.get(1));
    }

    /**
     * Method: get(int index)
     */
    @Test
    public void testGet() throws Exception {
        testArray.add(1);
        assertEquals(1, testArray.get(0));
    }

    /**
     * Method: remove(int index)
     */
    @Test
    public void testRemove() throws Exception {
        testArray.add(1);
        testArray.add(2);
        assertEquals(1, testArray.remove(0));
        assertEquals(2, testArray.remove(0));
    }

    /**
     * Method: size()
     */
    @Test
    public void testSize() throws Exception {
        testArray.add(1);
        testArray.add(2);
        assertEquals(2, testArray.size());
    }

}
