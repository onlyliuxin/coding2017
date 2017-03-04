package com.coding.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by mortimer on 2017/2/25.
 *
 */
public class ArrayListTest {

    private List<Integer> newList() {
        return new ArrayList<>();
    }

    @Test
    public void testAdd() {
        List<Integer> list = newList();
        Integer expectedValue = 100;
        list.add(expectedValue);

        System.out.println(list);
        Assert.assertEquals(expectedValue, list.get(0));
    }

    @Test
    public void testSize() {
        List<Integer> list = newList();

        list.add(100);
        list.add(90);
        list.add(80);

        System.out.println(list);
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void testRemove() {
        List<Integer> list = newList();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Assert.assertEquals(10, list.size());

        Integer remove = list.remove(4);
        Assert.assertEquals(remove, Integer.valueOf(4));
        Assert.assertEquals(Integer.valueOf(5), list.get(4));
        Assert.assertEquals(9, list.size());

        System.out.println(list);
    }

    @Test
    public void testAddAssignIndex() {
        List<Integer> list = newList();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        Assert.assertEquals(Integer.valueOf(4), list.get(4));

        list.add(4, 5);

        System.out.println(list);
        Assert.assertEquals(Integer.valueOf(5), list.get(4));
        Assert.assertEquals(21, list.size());
    }

    @Test
    public void testAddForAutoResize() {
        List<Integer> list = newList();

        int expectSize = 100;
        for (int i = 0; i < expectSize; i++) {
            list.add(i);
        }

        System.out.println(list);
        Assert.assertEquals(expectSize, list.size());
    }

    @Test
    public void testIterator() {
        List<Integer> list = newList();

        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
