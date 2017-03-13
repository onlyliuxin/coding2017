package com.coding.weak1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Administrator on 2017/3/11 0011.
 */
public class ArrayListTest{

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    private List list = new ArrayList();
    @Before
    public void setup(){

    }
    @Test
    public void testAdd() throws Exception {
        List list = new ArrayList();
        Assert.assertTrue(list.size() == 0);
        list.add(100);
        Assert.assertTrue(list.size()==1);

        for(int i = 0; i < 100; i++){
            list.add(i);
        }

        Assert.assertTrue(list.size() == 101 );

        list = new ArrayList();
        for(int i = 0; i < 1000000; i++){
            list.add(i);
        }
        Assert.assertTrue(list.size() == 1000000);
        Assert.assertTrue((int) list.get(1) == 1);
        Assert.assertTrue((int)list.get(100) == 100);
        Assert.assertTrue((int)list.get(1000) == 1000);
        Assert.assertTrue((int) list.get(9999) == 9999);
        Assert.assertTrue((int) list.get(999999) == 999999);
    }

    @Test
    public void testAdd1() throws Exception {
        List list = new ArrayList();
        Assert.assertTrue(list.size() == 0);
        for(int i = 0; i < 10; i++){
            list.add(i);
        }
        list.add(3, 9);
        Assert.assertEquals(list.get(3), 9);
        Assert.assertEquals(list.get(4), 3);
        Assert.assertTrue(list.size() == 11);
        list = new ArrayList();
        for(int i = 0; i < 10; i++){
            list.add(i);
        }

        thrown.expect(IndexOutOfBoundsException.class);
        list.add(10, 10);
    }


    @Test
    public void indexCheckForAdd2(){
        List list = new ArrayList();
        for(int i = 0; i < 10; i++){
            list.add(i);
        }
        thrown.expect(IndexOutOfBoundsException.class);
        list.add(10,10);
    }


    @Test
    public void indexCheck1(){
        List list = new ArrayList();
        for(int i = 0; i < 10; i++){
            list.add(i);
        }
        thrown.expect(IndexOutOfBoundsException.class);
        list.get(-1);
    }


    @Test
    public void testGet() throws Exception {
        List list = new ArrayList();
        for(int i = 0; i < 10; i++){
            list.add(i);
        }
        Assert.assertEquals(list.get(1), 1);
        Assert.assertEquals(list.get(9), 9);
    }

    @Test
    public void testRemove() throws Exception {
        List list = new ArrayList();
        for (int i = 0; i < 10; i++){
            list.add(i);
        }
        list.remove(1);
        Assert.assertEquals(list.get(1), 2);
    }


    @Test
    public void testIter() {
        List list = new ArrayList();
        for (int i = 0; i < 5; i++){
            list.add(i);
        }
        Iterator iterator = list.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(iterator.next(), 0);
        Assert.assertEquals(iterator.next(), 1);
        Assert.assertEquals(iterator.next(), 2);
        Assert.assertEquals(iterator.next(), 3);
        Assert.assertEquals(iterator.next(), 4);
        Assert.assertTrue(!iterator.hasNext());
    }
}