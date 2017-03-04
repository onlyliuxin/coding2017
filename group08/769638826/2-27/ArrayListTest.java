package com.coding.basic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by huitailang on 17/2/25.
 * test arraylist
 */
public class ArrayListTest {
    ArrayList arrayList = null;

    @Before
    public void setUp() {
        arrayList = new ArrayList();
    }

    @Test
    public void testArrayLength() {
        int[] array = new int[10];
        Assert.assertEquals(10, array.length);
    }

    @Test
    public void testAddElement() {
        arrayList.add(11);
        Assert.assertEquals(11, arrayList.get(0));
        printElementSize(arrayList);

        for (int i = 0; i < 18; i++) {

        }
    }

    @Test
    public void testAriseArray() {
        for (int i = 0; i < 18; i++) {
            arrayList.add(i + 1);
        }

        Assert.assertEquals(18, arrayList.size());

        for (int i = 0; i < 18; i++) {
            System.out.println(arrayList.get(i));
        }
    }

    @Test
    public void testRemoveElement() {
        for (int i = 0; i < 18; i++) {
            arrayList.add(i + 1);
        }

        Assert.assertEquals(18, arrayList.size());

        arrayList.remove(17);

        Assert.assertEquals(17, arrayList.size());

        for (int i = 0; i < 18; i++) {
            System.out.println(arrayList.get(i));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInValidGet() {
        arrayList.get(19);
    }

    private void printElementSize(ArrayList arrayList) {
        System.out.println("array size => " + arrayList.size());
    }
}
