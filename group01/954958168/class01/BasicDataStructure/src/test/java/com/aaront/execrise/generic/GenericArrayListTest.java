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
        Assert.assertEquals("1", arrayList.get(0));
        Assert.assertEquals("2", arrayList.get(1));
        Assert.assertEquals("3", arrayList.get(2));
        Assert.assertEquals(3, arrayList.size());
    }

    @Test
    public void testAddIndex() {
        arrayList.add(1, "4");
        arrayList.add(2, "5");
        Assert.assertArrayEquals(new String[]{"1", "4", "5", "2", "3" }, arrayList.toArray());
    }

    @Test
    public void testToArray() {
        Assert.assertArrayEquals(new String[]{"1", "2", "3" }, arrayList.toArray());
    }

    @Test
    public void testToGenericArray() {
        Assert.assertArrayEquals(new String[]{"1", "2", "3" }, arrayList.toArray(new String[0]));
    }

    @Test
    public void testGet() {
        Assert.assertEquals("3", arrayList.get(2));
        Assert.assertEquals("1", arrayList.get(0));
        Assert.assertEquals("2", arrayList.get(1));
    }

    @Test
    public void testRemove() {
        testAddIndex();
        arrayList.remove(2);
        arrayList.add(4, "10");
        arrayList.add(3, "9");
        Assert.assertArrayEquals(new String[]{"1", "4", "2", "9", "3", "10" }, arrayList.toArray());
    }

    @Test
    public void testIterator() {
        GenericIterator<String> genericIterator = arrayList.iterator();
        while (genericIterator.hasNext()) {
            genericIterator.next();
            genericIterator.remove();
        }
        Assert.assertArrayEquals(new String[]{}, arrayList.toArray());
    }

}
