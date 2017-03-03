package com.coding2017.group7.homework.c0226;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyLinkedListTest {

    private MyLinkedList myList = new MyLinkedList();
    private Object[] elements = new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    private final int mySize = elements.length;

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < mySize; i++) {
            myList.add(i, i + 1);
        }
    }

    @After
    public void tearDown() throws Exception {
        for (int i = myList.size(); i > 0; i--) {
            myList.remove(i - 1);
        }
        myList = null;
    }

    @Test
    public void add() throws Exception {
        myList.add(-1);
        Assert.assertEquals(myList.size(), mySize + 1);
    }

    @Test
    public void addIndex() throws Exception {
        int pos = mySize / 2;
        Object before = myList.get(pos);
        myList.add(pos, -1);
        Object after = myList.get(pos + 1);
        Assert.assertEquals(myList.size(), mySize + 1);
        Assert.assertEquals(before, after);
    }

    @Test
    public void remove() throws Exception {
        myList.remove(0);
        myList.remove(myList.size() - 1);
        myList.remove(myList.size() / 2);
        Assert.assertEquals(myList.size(), mySize - 3);
    }

    @Test
    public void addFirst() throws Exception {
        myList.addFirst(-1);
        myList.addFirst(-1);
        myList.addFirst(-1);
        Object o1 = myList.get(0);
        Object o2 = myList.get(1);
        Object o3 = myList.get(2);
        Assert.assertTrue(o1.equals(o2));
        Assert.assertTrue(o2.equals(o3));
        Assert.assertTrue(o3.equals(-1));
        Assert.assertEquals(myList.size(), mySize + 3);
    }

    @Test
    public void addLast() throws Exception {
        myList.addLast(-1);
        myList.addLast(-1);
        myList.addLast(-1);
        Object o1 = myList.get(myList.size() - 1);
        Object o2 = myList.get(myList.size() - 2);
        Object o3 = myList.get(myList.size() - 3);
        Assert.assertTrue(o1.equals(o2));
        Assert.assertTrue(o2.equals(o3));
        Assert.assertTrue(o3.equals(-1));
        Assert.assertEquals(myList.size(), mySize + 3);
    }

    @Test
    public void removeFirst() throws Exception {
        myList.addFirst(-1);
        Object o = myList.removeFirst();
        Assert.assertTrue(o.equals(-1));
        Assert.assertEquals(myList.size(), mySize);
    }

    @Test
    public void removeLast() throws Exception {
        myList.addLast(-1);
        Object o = myList.removeLast();
        Assert.assertTrue(o.equals(-1));
        Assert.assertEquals(myList.size(), mySize);
    }

    @Test
    public void iterator() throws Exception {
        MyIterator iterator = myList.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            count++;
        }
        Assert.assertEquals(mySize, count);
    }

}