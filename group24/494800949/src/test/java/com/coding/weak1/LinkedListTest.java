package com.coding.weak1;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Administrator on 2017/3/11 0011.
 */
public class LinkedListTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void testAdd() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.add("ssss");
        linkedList.add("ssss1");
        linkedList.add("ssss2");
        linkedList.add("ssss3");
        linkedList.add("ssss4");
        linkedList.add("ssss5");
        Assert.assertEquals(linkedList.size(), 6);
        Assert.assertEquals(linkedList.get(0), "ssss");
        Assert.assertEquals(linkedList.get(1), "ssss1");
        Assert.assertEquals(linkedList.get(3), "ssss3");
        Assert.assertEquals(linkedList.get(5), "ssss5");
    }

    @Test
    public void testAdd1() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.add("ssss");
        linkedList.add("ssss1");
        linkedList.add("ssss2");
        linkedList.add(1, "ssss3");
        Assert.assertEquals(linkedList.get(0), "ssss");
        Assert.assertEquals(linkedList.get(1), "ssss3");
        Assert.assertEquals(linkedList.get(2), "ssss1");
        Assert.assertEquals(linkedList.get(3), "ssss2");
    }

    @Test
    public void testGet() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.addFirst("ssss");
        linkedList.addFirst("ssss1");
        linkedList.addFirst("ssss2");
        linkedList.addFirst("ssss3");
        linkedList.addFirst("ssss4");
        linkedList.addFirst("ssss5");
        Assert.assertEquals(linkedList.size(), 6);
        Assert.assertEquals(linkedList.get(0), "ssss5");
        Assert.assertEquals(linkedList.get(1), "ssss4");
        Assert.assertEquals(linkedList.get(3), "ssss2");
        Assert.assertEquals(linkedList.get(5), "ssss");
//        thrown.expect(IndexOutOfBoundsException.class);
//        linkedList.get(-1);
        thrown.expect(IndexOutOfBoundsException.class);
        linkedList.get(6);
    }

    @Test
    public void testRemove() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.add("ssss");
        linkedList.add("ssss1");
        linkedList.add("ssss2");
        linkedList.add("ssss3");
        linkedList.add("ssss4");
        linkedList.add("ssss5");
        String ret = (String)linkedList.remove(3);
        Assert.assertEquals(ret, "ssss3");
        Assert.assertEquals(linkedList.size(), 5);
        Assert.assertEquals(linkedList.get(3),"ssss4");
        Assert.assertEquals(linkedList.get(2),"ssss2");
//
        Assert.assertEquals(linkedList.size(), 5);
        linkedList.remove(4);
        Assert.assertEquals(linkedList.get(3), "ssss4");
    }

    @Test
    public void testSize() throws Exception {
        LinkedList linkedList = new LinkedList();
        Assert.assertEquals(linkedList.size(), 0);
    }

    @Test
    public void testAddFirst() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.addFirst("ssss");
        linkedList.addFirst("ssss1");
        linkedList.addFirst("ssss2");
        linkedList.addFirst("ssss3");
        linkedList.addFirst("ssss4");
        linkedList.addFirst("ssss5");
        Assert.assertEquals(linkedList.size(), 6);
    }

    @Test
    public void testAddLast() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast("ssss");
        linkedList.addLast("ssss1");
        linkedList.addLast("ssss1");
        linkedList.addLast("ssss1");
        linkedList.addLast("ssss1");
        linkedList.addLast("ssss1");
        Assert.assertEquals(linkedList.size(), 6);
    }

    @Test
    public void testRemoveFirst() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.add("ssss");
        linkedList.add("ssss1");
        Assert.assertEquals(linkedList.size(), 2);
        linkedList.removeFirst();
        Assert.assertEquals(linkedList.size(), 1);
        Assert.assertEquals(linkedList.get(0), "ssss1");
        linkedList.removeFirst();
        Assert.assertEquals(linkedList.size(), 0);
        thrown.expect(IndexOutOfBoundsException.class);
        Assert.assertEquals(linkedList.get(0), "ssss1");
    }

    @Test
    public void testRemoveLast() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.add("ssss");
        linkedList.add("ssss1");
        linkedList.add("ssss2");
        Assert.assertEquals(linkedList.size(), 3);
        Assert.assertEquals(linkedList.get(2), "ssss2");
        linkedList.removeLast();
        Assert.assertEquals(linkedList.size(), 2);
        thrown.expect(IndexOutOfBoundsException.class);
        Assert.assertEquals(linkedList.get(2), "ssss2");
    }

    @Test
    public void testIterator() throws Exception {
        List list = new LinkedList();
        list.add("ssss");
        list.add("ssss1");
        list.add("ssss2");
        Iterator iterator = list.iterator();
        Assert.assertEquals(iterator.next(), "ssss");
        Assert.assertEquals(iterator.next(), "ssss1");
        Assert.assertEquals(iterator.next(), "ssss2");
        thrown.expect(IndexOutOfBoundsException.class);
        Assert.assertEquals(iterator.next(), "ssss2");
    }
}