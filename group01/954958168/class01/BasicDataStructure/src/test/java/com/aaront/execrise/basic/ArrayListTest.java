package com.aaront.execrise.basic;

import com.aaront.exercise.basic.ArrayList;
import com.aaront.exercise.basic.Iterator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tonyhui
 * @since 17/2/20
 */
public class ArrayListTest {

    private ArrayList arrayList = new ArrayList();

    @Before
    public void init() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(arrayList.get(0), 1);
        Assert.assertEquals(arrayList.get(1), 2);
        Assert.assertEquals(arrayList.get(2), 3);
        Assert.assertEquals(arrayList.size(), 3);
    }

    @Test
    public void testAddIndex() {
        arrayList.add(1, 4);
        arrayList.add(2, 5);
        Assert.assertArrayEquals(arrayList.toArray(), new Object[]{1, 4, 5, 2, 3});
    }

    @Test
    public void testToArray() {
        Assert.assertArrayEquals(arrayList.toArray(), new Object[]{1, 2, 3});
    }

    @Test
    public void testGet() {
        Assert.assertEquals(arrayList.get(2), 3);
        Assert.assertEquals(arrayList.get(0), 1);
        Assert.assertEquals(arrayList.get(1), 2);
    }

    @Test
    public void testRemove() {
        testAddIndex();
        arrayList.remove(2);
        arrayList.add(4, 10);
        arrayList.add(3, 9);
        Assert.assertArrayEquals(arrayList.toArray(), new Object[]{1, 4, 2, 9, 3, 10});
    }

    @Test
    public void testIterator() {
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        Assert.assertArrayEquals(arrayList.toArray(), new Object[]{});
    }
}
