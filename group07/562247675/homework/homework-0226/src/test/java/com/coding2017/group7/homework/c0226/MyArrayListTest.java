package com.coding2017.group7.homework.c0226;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyArrayListTest {

    private MyArrayList myList = new MyArrayList();
    private final int mySize = 15;

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < mySize; i++) {
            myList.add(i);
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
    public void addLast() throws Exception {
        int element = -1;
        myList.add(element);
        Assert.assertEquals(myList.size(), mySize + 1);
        Assert.assertTrue(myList.get(myList.size() - 1).equals(element));
    }

    @Test
    public void addIndex() throws Exception {
        int index = mySize / 2;
        int element = -1;
        myList.add(index, element);
        Assert.assertTrue(myList.get(index).equals(element));
        Assert.assertEquals(myList.size(), mySize + 1);
    }

    @Test
    public void get() throws Exception {
        Assert.assertTrue(myList.get(0).equals(0));
    }

    @Test
    public void remove() throws Exception {
        int index = mySize / 2;
        Object before = myList.get(index + 1);
        Object element = myList.remove(index);
        Object after = myList.get(index);
        Assert.assertTrue(before.equals(after));
        Assert.assertEquals(myList.size(), mySize - 1);

    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(myList.size(), mySize);
    }

    @Test
    public void iterator() throws Exception {
        MyIterator iterator = myList.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        Assert.assertEquals(mySize, count);
    }

}