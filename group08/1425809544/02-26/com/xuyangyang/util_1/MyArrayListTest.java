package com.util_1;


import org.junit.Assert;

/**
 * Created by 14258 on 2017/2/27.
 */
public class MyArrayListTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testAdd() throws Exception {

        MyArrayList myArrayList = new MyArrayList();

        myArrayList.add("1");
        myArrayList.add("1");
        myArrayList.add("1");
        myArrayList.add("1");

        System.out.println(myArrayList.size());
        System.out.println(myArrayList.get(0));
        MyIterator iterator = myArrayList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @org.junit.Test
    public void testAdd1() throws Exception {

    }

    @org.junit.Test
    public void testRemove() throws Exception {

    }

    @org.junit.Test
    public void testGet() throws Exception {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add("00");
        Object o = myArrayList.get(0);
        myArrayList.remove(0);
        System.out.println(o);
    }

    @org.junit.Test
    public void testSize() throws Exception {
        MyArrayList testArrayList = new MyArrayList();
        testArrayList.add("11");
        int size =  testArrayList.size();
        Assert.assertEquals(size,1);
    }
}