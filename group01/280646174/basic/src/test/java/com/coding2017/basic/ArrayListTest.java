package com.coding2017.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by kaitao.li on 17/2/21.
 */
public class ArrayListTest {
    @Test
    public void testAdd() throws Exception {
        ArrayList arrayList = new ArrayList();
        arrayList.add("0");
        Assert.assertTrue(arrayList.get(0).equals("0"));
    }

    @Test
    public void testAddWithIndex() throws Exception {
        ArrayList arrayList = new ArrayList();
        arrayList.add("0");
        arrayList.add("1");
        arrayList.add(1, "2");
        Assert.assertTrue(arrayList.get(1).equals("2"));
        Assert.assertTrue(arrayList.get(2).equals("1"));
        Assert.assertTrue(arrayList.size() == 3);
    }

    @Test
    public void get() throws Exception {
        ArrayList arrayList = new ArrayList();
        arrayList.add("0");
        arrayList.add("1");
        Assert.assertTrue(arrayList.get(1).equals("1"));
    }

    @Test
    public void remove() throws Exception {
        ArrayList arrayList = new ArrayList();
        arrayList.add("0");
        arrayList.add("1");
        arrayList.add("2");
        Object remove = arrayList.remove(1);
        Assert.assertTrue(remove.equals("1"));
        Assert.assertTrue(arrayList.size() == 2);
    }

    @Test
    public void size() throws Exception {
        ArrayList arrayList = new ArrayList();
        arrayList.add("0");
        arrayList.add("1");
        Assert.assertEquals(arrayList.size(), 2);
    }

    @Test
    public void testExtend() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("0");
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        Assert.assertTrue(arrayList.get(4).equals("4"));
    }

    @Test
    public void iterator() throws Exception {
        ArrayList arrayList = new ArrayList();
        arrayList.add("0");
        arrayList.add("1");
        Iterator iterator = arrayList.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.next().equals("0"));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.next().equals("1"));
        Assert.assertTrue(!iterator.hasNext());
    }

}