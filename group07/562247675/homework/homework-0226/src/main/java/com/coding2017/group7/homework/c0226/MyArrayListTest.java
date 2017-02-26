package com.coding2017.group7.homework.c0226;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyArrayListTest {

    private MyArrayList mylist = new MyArrayList();
    private final int mysize = 15;

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < mysize; i++) {
            mylist.add(i);
        }
    }

    @After
    public void tearDown() throws Exception {
        for (int i = mylist.size(); i > 0; i--) {
            mylist.remove(i - 1);
        }
        mylist = null;
    }

    @Test
    public void addLast() throws Exception {
        int element = -1;
        mylist.add(element);
        Assert.assertEquals(mylist.size(), mysize + 1);
        Assert.assertTrue(mylist.get(mylist.size() - 1).equals(element));
    }

    @Test
    public void addIndex() throws Exception {
        int index = mysize / 2;
        int element = -1;
        mylist.add(index, element);
        Assert.assertTrue(mylist.get(index).equals(element));
        Assert.assertEquals(mylist.size(), mysize + 1);
    }

    @Test
    public void get() throws Exception {
        Assert.assertTrue(mylist.get(0).equals(0));
    }

    @Test
    public void remove() throws Exception {
        int index = mysize / 2;
        Object before = mylist.get(index + 1);
        Object element = mylist.remove(index);
        Object after = mylist.get(index);
        Assert.assertTrue(before.equals(after));
        Assert.assertEquals(mylist.size(), mysize - 1);

    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(mylist.size(), mysize);
    }

    @Test
    public void iterator() throws Exception {
        MyIterator iterator = mylist.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        Assert.assertEquals(mylist.size(), count);
    }

}