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
}
