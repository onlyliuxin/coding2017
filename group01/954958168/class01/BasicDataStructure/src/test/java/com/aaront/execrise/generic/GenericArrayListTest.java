package com.aaront.execrise.generic;

import com.aaront.exercise.generic.GenericArrayList;
import com.aaront.exercise.generic.GenericIterator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tonyhui
 * @since 17/2/22
 */
public class GenericArrayListTest {

    private GenericArrayList<String> arrayList = new GenericArrayList<>();

    @Before
    public void init() {
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
    }


    @Test
    public void testAdd() {
        Assert.assertEquals(arrayList.get(0), "1");
        Assert.assertEquals(arrayList.get(1), "2");
        Assert.assertEquals(arrayList.get(2), "3");
        Assert.assertEquals(arrayList.size(), 3);
    }

    @Test
    public void testAddIndex() {
        arrayList.add(1, "4");
        arrayList.add(2, "5");
        Assert.assertArrayEquals(arrayList.toArray(), new String[]{"1", "4", "5", "2", "3"});
    }

    @Test
    public void testToArray() {
        Assert.assertArrayEquals(arrayList.toArray(), new String[]{"1", "2", "3"});
    }

    @Test
    public void testToGenericArray() {
        Assert.assertArrayEquals(arrayList.toArray(new String[0]), new String[]{"1", "2", "3"});
    }

    @Test
    public void testGet() {
        Assert.assertEquals(arrayList.get(2), "3");
        Assert.assertEquals(arrayList.get(0), "1");
        Assert.assertEquals(arrayList.get(1), "2");
    }

    @Test
    public void testRemove() {
        testAddIndex();
        arrayList.remove(2);
        arrayList.add(4, "10");
        arrayList.add(3, "9");
        Assert.assertArrayEquals(arrayList.toArray(), new String[]{"1", "4", "2", "9", "3", "10"});
    }

    @Test
    public void testIterator() {
        GenericIterator<String> genericIterator = arrayList.iterator();
        while (genericIterator.hasNext()) {
            genericIterator.next();
            genericIterator.remove();
        }
        Assert.assertArrayEquals(arrayList.toArray(), new String[]{});
    }

}
