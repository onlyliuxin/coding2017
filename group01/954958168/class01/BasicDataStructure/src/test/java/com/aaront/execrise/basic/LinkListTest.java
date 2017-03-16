package com.aaront.execrise.basic;

import com.aaront.exercise.basic.Iterator;
import com.aaront.exercise.basic.LinkedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tonyhui
 * @since 17/2/21
 */
public class LinkListTest {

    private LinkedList linkedList = new LinkedList();

    @Before
    public void init() {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
    }

    @Test
    public void testAdd() {
        Assert.assertArrayEquals(new Object[]{1, 2, 3}, linkedList.toArray());
    }

    @Test
    public void testAddIndex() {
        linkedList.add(1, 10);
        linkedList.add(0, 8);
        Assert.assertArrayEquals(new Object[]{8, 1, 10, 2, 3}, linkedList.toArray());
    }

    @Test
    public void testAddFirst() {
        linkedList.addFirst(-1);
        Assert.assertArrayEquals(new Object[]{-1, 1, 2, 3}, linkedList.toArray());
    }

    @Test
    public void testAddLast() {
        linkedList.addLast(99);
        Assert.assertArrayEquals(new Object[]{1, 2, 3, 99}, linkedList.toArray());
    }

    @Test
    public void testRemove() {
        testAddIndex();
        linkedList.remove(1);
        linkedList.remove(2);
        linkedList.add(3, 3);
        linkedList.add(1, 2);
        Assert.assertArrayEquals(new Object[]{8, 2, 10, 3, 3}, linkedList.toArray());
    }

    @Test
    public void testRemoveFirst() {
        linkedList.removeFirst();
        linkedList.removeFirst();
        Assert.assertArrayEquals(new Object[]{3}, linkedList.toArray());
    }

    @Test
    public void testRemoveLast() {
        linkedList.removeLast();
        linkedList.removeLast();
        Assert.assertArrayEquals(new Object[]{1}, linkedList.toArray());
    }

    @Test
    public void testIterator() {
        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        Assert.assertArrayEquals(new Object[]{}, linkedList.toArray());
    }

    @Test
    public void testReverse() {
        linkedList.reverse();
        Assert.assertArrayEquals(new Object[]{3, 2, 1}, linkedList.toArray());
    }

    @Test
    public void testRemoveFirstHalf() {
        linkedList.removeFirstHalf();
        Assert.assertArrayEquals(new Object[]{2, 3}, linkedList.toArray());
    }

    @Test
    public void testRangeRemove() {
        linkedList.remove(1, 4);
        Assert.assertArrayEquals(new Object[]{1}, linkedList.toArray());
    }

    @Test
    public void testGetElements() {
        LinkedList sub = new LinkedList();
        sub.add(1);
        sub.add(3);
        sub.add(4);
        int[] result = linkedList.getElements(sub);
        Assert.assertArrayEquals(new int[]{2, 4, 5}, result);
    }

    @Test
    public void testSubtract() {
        LinkedList sub = new LinkedList();
        sub.add(1);
        sub.add(2);
        sub.add(10);
        linkedList.subtract(sub);
        Assert.assertArrayEquals(new Object[]{3, 4, 5}, linkedList.toArray());
    }

    @Test
    public void testRemoveDuplicateValues() {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(6);
        list.add(7);
        list.add(7);
        list.add(7);
        list.add(7);
        list.add(7);
        list.removeDuplicateValues();
        Assert.assertArrayEquals(new Object[]{1, 2, 3, 4, 5, 6, 7}, list.toArray());
    }

    @Test
    public void testRemoveRange() {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(7);
        list.add(8);
        list.removeRange(1, 4);
        Assert.assertArrayEquals(new Object[]{1, 4, 5, 6, 7, 7, 8}, list.toArray());
    }

    @Test
    public void testIntersection() {
        LinkedList list1 = new LinkedList();
        list1.add(1);
        list1.add(3);
        list1.add(5);
        list1.add(7);
        list1.add(9);
        LinkedList list2 = new LinkedList();
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(6);
        list2.add(7);
        list2.add(8);
        list2.add(10);
        list2.add(11);
        list2.add(12);
        LinkedList intersection = list1.intersection(list2);
        Assert.assertArrayEquals(new Object[]{3, 5, 7}, intersection.toArray());
    }

}
