package com.coderising.LinkList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by damocles on 2017/3/7.
 */
public class LinkedListTest {
    private LinkedList<Integer> linkedList;

    @Before
    public void setUp() throws Exception {
        linkedList = new LinkedList<>();

        linkedList.add(101);
        linkedList.add(201);
        linkedList.add(301);
        linkedList.add(401);
        linkedList.add(501);
        linkedList.add(601);
        linkedList.add(701);
    }

    @After
    public void tearDown() throws Exception {
        linkedList = null;
    }

    @Test
    public void reverse() throws Exception {
        linkedList.reverse();

        Assert.assertEquals(701, (int) linkedList.get(0));
        Assert.assertEquals(601, (int) linkedList.get(1));
        Assert.assertEquals(501, (int) linkedList.get(2));
        Assert.assertEquals(401, (int) linkedList.get(3));
        Assert.assertEquals(301, (int) linkedList.get(4));
        Assert.assertEquals(201, (int) linkedList.get(5));
        Assert.assertEquals(101, (int) linkedList.get(6));

    }

    @Test
    public void removeFirstHalf() throws Exception {
        linkedList.removeFirstHalf();

        Assert.assertEquals(501, (int) linkedList.get(0));
        Assert.assertEquals(3, linkedList.size());
    }

    @Test
    public void remove() throws Exception {
        linkedList.remove(0, 3);

        Assert.assertEquals(4, linkedList.size());
        Assert.assertEquals(401, (int) linkedList.get(0));

        linkedList.remove(1, 3);

        Assert.assertEquals(1, linkedList.size());
        Assert.assertEquals(401, (int) linkedList.get(0));
    }

    @Test
    public void getElements() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(0);
        list.add(2);
        list.add(4);
        list.add(6);
        int[] ints = new int[]{101, 301, 501, 701};

        Assert.assertArrayEquals(ints, linkedList.getElements(list));
    }

    @Test
    public void subtract() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(101);
        list.add(301);
        list.add(401);
        list.add(601);

        linkedList.subtract(list);

        Assert.assertEquals(3, linkedList.size());
        Assert.assertEquals(201, (int) linkedList.get(0));
        Assert.assertEquals(701, (int) linkedList.get(2));
    }

    @Test
    public void removeDuplicateValues() throws Exception {
        linkedList.removeDuplicateValues();

        Assert.assertEquals(7, linkedList.size());

        linkedList.add(701);
        linkedList.add(801);
        linkedList.add(901);
        linkedList.add(901);
        linkedList.add(901);
        linkedList.removeDuplicateValues();

        Assert.assertEquals(9, linkedList.size());
        Assert.assertEquals(901, (int) linkedList.get(8));
        Assert.assertEquals(801, (int) linkedList.get(7));
        Assert.assertEquals(701, (int) linkedList.get(6));
        Assert.assertEquals(301, (int) linkedList.get(2));
    }

    @Test
    public void removeRange() throws Exception {
        linkedList.removeRange(101, 601);

        Assert.assertEquals(3, linkedList.size());
        Assert.assertEquals(101, (int) linkedList.get(0));
        Assert.assertEquals(601, (int) linkedList.get(1));
        Assert.assertEquals(701, (int) linkedList.get(2));
    }

    @Test
    public void intersection() throws Exception {
        LinkedList<Integer> linkedList2 = new LinkedList<>();
        linkedList2.add(301);
        linkedList2.add(401);

        LinkedList<Integer> newList = linkedList.intersection(linkedList2);

        Assert.assertEquals(2, newList.size());
        Assert.assertEquals(301, (int) newList.get(0));
        Assert.assertEquals(401, (int) newList.get(1));
    }

}