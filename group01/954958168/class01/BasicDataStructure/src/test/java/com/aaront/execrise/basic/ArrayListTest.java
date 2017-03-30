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
        Assert.assertEquals(1, arrayList.get(0));
        Assert.assertEquals(2, arrayList.get(1));
        Assert.assertEquals(3, arrayList.get(2));
        Assert.assertEquals(3, arrayList.size());
    }

    @Test
    public void testAddIndex() {
        arrayList.add(1, 4);
        arrayList.add(2, 5);
        Assert.assertArrayEquals(new Object[]{1, 4, 5, 2, 3}, arrayList.toArray());
    }

    @Test
    public void testToArray() {
        Assert.assertArrayEquals(new Object[]{1, 2, 3}, arrayList.toArray());
    }

    @Test
    public void testGet() {
        Assert.assertEquals(3, arrayList.get(2));
        Assert.assertEquals(1, arrayList.get(0));
        Assert.assertEquals(2, arrayList.get(1));
    }

    @Test
    public void testRemove() {
        testAddIndex();
        arrayList.remove(2);
        arrayList.add(4, 10);
        arrayList.add(3, 9);
        Assert.assertArrayEquals(new Object[]{1, 4, 2, 9, 3, 10}, arrayList.toArray());
    }

    @Test
    public void testIterator() {
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        Assert.assertArrayEquals(new Object[]{}, arrayList.toArray());
    }
}
