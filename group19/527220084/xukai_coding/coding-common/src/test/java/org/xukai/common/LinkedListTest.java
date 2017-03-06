package org.xukai.common;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xukai
 * @desc
 * @date 2017-02-20-下午 3:54
 */
public class LinkedListTest {

    @Test
    public void testAdd() throws Exception {
        LinkedList list = new LinkedList();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        Assert.assertTrue(list.size() == 5);
        list.add(0,"000");
        Assert.assertTrue( (list.get(0)).equals("000"));
        Assert.assertTrue(list.size() == 6);
        list.addFirst("111");
        Assert.assertTrue(list.get(0).equals("111"));
        list.remove(5);
        Assert.assertTrue(list.size() == 6);
        list.addLast("111");
        Assert.assertTrue(list.size() == 7);
        list.removeFirst();
        Assert.assertTrue(list.size() == 6);
        list.removeLast();
        Assert.assertTrue(list.size() == 5);
        list.remove(4);
        Assert.assertTrue(list.size() == 4);
        list.display();
    }

    @Test
    public void testAdd1() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testRemove() throws Exception {

    }

    @Test
    public void testSize() throws Exception {

    }

    @Test
    public void testAddFirst() throws Exception {

    }

    @Test
    public void testAddLast() throws Exception {

    }

    @Test
    public void testRemoveFirst() throws Exception {

    }

    @Test
    public void testRemoveLast() throws Exception {

    }

    @Test
    public void testIterator() throws Exception {

    }

    @Test
    public void testDisplay() throws Exception {

    }
}