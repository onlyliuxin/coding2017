package com.coding2017.group7.homework.c0226;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyArrayListTest {

<<<<<<< HEAD
    private MyArrayList mylist = new MyArrayList();
    private final int mysize = 15;

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < mysize; i++) {
            mylist.add(i);
=======
    private MyArrayList myList = new MyArrayList();
    private Object[] elements = new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    private final int mySize = elements.length;

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < mySize; i++) {
            myList.add(i, i + 1);
>>>>>>> 6f77b0da09652d5cbdf03d5d53e197580ced1c5a
        }
    }

    @After
    public void tearDown() throws Exception {
<<<<<<< HEAD
        for (int i = mylist.size(); i > 0; i--) {
            mylist.remove(i - 1);
        }
        mylist = null;
=======
        for (int i = myList.size(); i > 0; i--) {
            myList.remove(i - 1);
        }
        myList = null;
>>>>>>> 6f77b0da09652d5cbdf03d5d53e197580ced1c5a
    }

    @Test
    public void addLast() throws Exception {
        int element = -1;
<<<<<<< HEAD
        mylist.add(element);
        Assert.assertEquals(mylist.size(), mysize + 1);
        Assert.assertTrue(mylist.get(mylist.size() - 1).equals(element));
=======
        myList.add(element);
        Assert.assertEquals(myList.size(), mySize + 1);
        Assert.assertTrue(myList.get(myList.size() - 1).equals(element));
>>>>>>> 6f77b0da09652d5cbdf03d5d53e197580ced1c5a
    }

    @Test
    public void addIndex() throws Exception {
<<<<<<< HEAD
        int index = mysize / 2;
        int element = -1;
        mylist.add(index, element);
        Assert.assertTrue(mylist.get(index).equals(element));
        Assert.assertEquals(mylist.size(), mysize + 1);
    }

    @Test
    public void get() throws Exception {
        Assert.assertTrue(mylist.get(0).equals(0));
=======
        int index = mySize / 2;
        int element = -1;
        myList.add(index, element);
        Assert.assertTrue(myList.get(index).equals(element));
        Assert.assertEquals(myList.size(), mySize + 1);
>>>>>>> 6f77b0da09652d5cbdf03d5d53e197580ced1c5a
    }

    @Test
    public void remove() throws Exception {
<<<<<<< HEAD
        int index = mysize / 2;
        Object before = mylist.get(index + 1);
        Object element = mylist.remove(index);
        Object after = mylist.get(index);
        Assert.assertTrue(before.equals(after));
        Assert.assertEquals(mylist.size(), mysize - 1);
=======
        int index = mySize / 2;
        Object before = myList.get(index + 1);
        Object element = myList.remove(index);
        Object after = myList.get(index);
        Assert.assertTrue(before.equals(after));
        Assert.assertEquals(myList.size(), mySize - 1);
>>>>>>> 6f77b0da09652d5cbdf03d5d53e197580ced1c5a

    }

    @Test
<<<<<<< HEAD
    public void size() throws Exception {
        Assert.assertEquals(mylist.size(), mysize);
    }

    @Test
    public void iterator() throws Exception {
        MyIterator iterator = mylist.iterator();
=======
    public void iterator() throws Exception {
        MyIterator iterator = myList.iterator();
>>>>>>> 6f77b0da09652d5cbdf03d5d53e197580ced1c5a
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
<<<<<<< HEAD
        Assert.assertEquals(mylist.size(), count);
=======
        Assert.assertEquals(mySize, count);
>>>>>>> 6f77b0da09652d5cbdf03d5d53e197580ced1c5a
    }

}