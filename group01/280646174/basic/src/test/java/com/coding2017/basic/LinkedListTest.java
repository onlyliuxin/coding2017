package com.coding2017.basic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kaitao.li on 17/2/21.
 */
public class LinkedListTest {
    @Test
    public void testAdd() throws Exception {
        LinkedList list = new LinkedList();
        list.add("0");
        Assert.assertTrue(list.get(0).equals("0"));
    }

    @Test
    public void testAddWithIndex() throws Exception {
        LinkedList list = new LinkedList();
        list.add("0");
        list.add("1");
        list.add(1, "2");
        Assert.assertTrue(list.get(1).equals("2"));
        Assert.assertTrue(list.get(2).equals("1"));
        Assert.assertTrue(list.size() == 3);
    }

    @Test
    public void get() throws Exception {
        LinkedList list = new LinkedList();
        list.add("0");
        list.add("1");
        Assert.assertTrue(list.get(1).equals("1"));
    }

    @Test
    public void remove() throws Exception {
        LinkedList list = new LinkedList();
        list.add("0");
        list.add("1");
        list.add("2");
        Object remove = list.remove(1);
        Assert.assertTrue(remove.equals("1"));
        Assert.assertTrue(list.size() == 2);
    }

    @Test
    public void size() throws Exception {
        LinkedList list = new LinkedList();
        list.add("0");
        list.add("1");
        Assert.assertEquals(list.size(), 2);
    }

    @Test
    public void testAddFirst() {
        LinkedList list = new LinkedList();
        list.addFirst("0");
        Assert.assertTrue(list.get(0).equals("0"));
        list.addFirst("1");
        Assert.assertTrue(list.get(0).equals("1"));
        list.removeFirst();
        Assert.assertTrue(list.get(0).equals("0"));
        list.removeLast();
        Assert.assertTrue(list.size() == 0);
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