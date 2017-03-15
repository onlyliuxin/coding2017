package com.coding.basic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lqt0223 on 2017/3/9.
 */
public class LinkedListTest {
    LinkedList<Integer> linkedList = new LinkedList<>();

    @Before
    public void setup(){
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
    }

    @Test
    public void testAdd() throws Exception {
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(7);
        Assert.assertEquals(6, linkedList.size());
        Assert.assertEquals(new Integer(5), linkedList.get(3));
        Assert.assertEquals(new Integer(6), linkedList.get(4));
        Assert.assertEquals(new Integer(7), linkedList.get(5));
    }

    @Test
    public void testInsert() throws Exception {
        linkedList.insert(1, 3);
        Assert.assertEquals(4, linkedList.size());
        Assert.assertEquals(new Integer(3), linkedList.get(1));
    }

    @Test
    public void testGet() throws Exception {
        Assert.assertEquals(new Integer(2), linkedList.get(0));
        Assert.assertEquals(new Integer(3), linkedList.get(1));
        Assert.assertEquals(new Integer(4), linkedList.get(2));
    }

    @Test
    public void testRemoveAt() throws Exception {
        linkedList.remove(1);
        Assert.assertEquals(2, linkedList.size());
        Assert.assertEquals(new Integer(2), linkedList.get(0));
        Assert.assertEquals(new Integer(4), linkedList.get(1));
    }

    @Test
    public void testSize() throws Exception {
        Assert.assertEquals(3, linkedList.size());
    }

    @Test
    public void testAddFirst() throws Exception {
        linkedList.addFirst(1);
        Assert.assertEquals(4, linkedList.size());
        Assert.assertEquals(new Integer(1), linkedList.get(0));
    }

    @Test
    public void testAddLast() throws Exception {
        linkedList.addLast(5);
        Assert.assertEquals(4, linkedList.size());
        Assert.assertEquals(new Integer(5), linkedList.get(3));
    }

    @Test
    public void testRemoveFirst() throws Exception {
        linkedList.removeFirst();
        Assert.assertEquals(2, linkedList.size());
        Assert.assertEquals(new Integer(3), linkedList.get(0));
    }

    @Test
    public void testRemoveLast() throws Exception {
        linkedList.removeLast();
        Assert.assertEquals(new Integer(3), linkedList.get(1));
        try{
            linkedList.get(2);
            Assert.fail("Should throw IndexOutOfBoundsException");
        }catch (IndexOutOfBoundsException e){
//            e.printStackTrace();
        }
    }

    @Test
    public void testReverse() throws Exception {
        linkedList.reverse();
        Assert.assertEquals(new Integer(4), linkedList.get(0));
        Assert.assertEquals(new Integer(3), linkedList.get(1));
        Assert.assertEquals(new Integer(2), linkedList.get(2));
    }

    @Test
    public void testRemoveByLength() {
        linkedList.add(5);
        linkedList.remove(1,2);
        Assert.assertEquals(2, linkedList.size());
        Assert.assertEquals(new Integer(5), linkedList.get(1));
    }

    @Test
    public void testRemoveFirstHalfForOddArray() throws Exception {
        linkedList.removeFirstHalf();
        Assert.assertEquals(2, linkedList.size());
        Assert.assertEquals(new Integer(3), linkedList.get(0));
        Assert.assertEquals(new Integer(4), linkedList.get(1));
    }

    @Test
    public void testRemoveFirstHalfForEvenArray() throws Exception {
        linkedList.add(5);
        linkedList.removeFirstHalf();
        Assert.assertEquals(2, linkedList.size());
        Assert.assertEquals(new Integer(4), linkedList.get(0));
        Assert.assertEquals(new Integer(5), linkedList.get(1));
    }

    @Test
    public void testGetElements() throws Exception {
        LinkedList<Integer> b = new LinkedList<>();
        b.add(1);
        b.add(0);
        int[] result = linkedList.getElements(b);
        Assert.assertEquals(3, result[0]);
        Assert.assertEquals(2, result[1]);

    }

    @Test
    public void testSubtract() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(2);
        list.add(4);
        linkedList.subtract(list);
        Assert.assertEquals(1, linkedList.size());
        Assert.assertEquals(new Integer(3), linkedList.get(0));
    }

    @Test
    public void testRemoveDuplicateValues() throws Exception {
        linkedList.insert(1, 4);
        linkedList.add(3);

        linkedList.removeDuplicateValues();

        Assert.assertEquals(3, linkedList.size());
        Assert.assertEquals(new Integer(2), linkedList.get(0));
        Assert.assertEquals(new Integer(4), linkedList.get(1));
        Assert.assertEquals(new Integer(3), linkedList.get(2));
    }

    @Test
    public void testRemoveRange() throws Exception {
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(7);
        int min = 2;
        int max = 6;
        linkedList.removeRange(min, max);
        Assert.assertEquals(3, linkedList.size());
        Assert.assertEquals(new Integer(2), linkedList.get(0));
        Assert.assertEquals(new Integer(6), linkedList.get(1));
        Assert.assertEquals(new Integer(7), linkedList.get(2));
    }

    @Test
    public void intersection() throws Exception {
        linkedList.add(5);
        LinkedList<Integer> b = new LinkedList<>();
        b.add(3);
        b.add(4);
        b.add(5);
        b.add(6);
        LinkedList c = linkedList.intersection(b);
        Assert.assertEquals(3, c.size());
        Assert.assertEquals(new Integer(3), c.get(0));
        Assert.assertEquals(new Integer(4), c.get(1));
        Assert.assertEquals(new Integer(5), c.get(2));

    }

}