package com.coding.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by mortimer on 2017/2/26.
 *
 */
public class LinkedListTest {

    private LinkedList<Integer> newList() {
        return new LinkedList<>();
    }

    @Test
    public void testAdd() {
        List<Integer> list = newList();
        list.add(10);
        list.add(10);
        list.add(10);

        System.out.println(list);
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void testGet() {
        List<Integer> list = newList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        System.out.println(list);
        Assert.assertEquals(Integer.valueOf(4), list.get(4));
    }

    @Test
    public void testAddByAssign() {
        List<Integer> list = newList();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.add(4, 4);

        System.out.println(list);

        Assert.assertEquals(Integer.valueOf(4), list.get(4));
        Assert.assertEquals(Integer.valueOf(4), list.get(5));
    }

    @Test
    public void testRemove() {
        List<Integer> list = newList();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        list.remove(5);

        System.out.println(list);

        Assert.assertEquals(99, list.size());
        Assert.assertEquals(Integer.valueOf(6), list.get(5));
    }

    @Test
    public void testAddFirst() {
        LinkedList<Integer> list = newList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.addFirst(10);
        System.out.println(list);

        Assert.assertEquals(11, list.size());
        Assert.assertEquals(Integer.valueOf(10), list.get(0));
    }

    @Test
    public void testAddLast() {
        LinkedList<Integer> list = newList();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        list.addLast(10);

        System.out.println(list);
        Assert.assertEquals(21, list.size());
        Assert.assertEquals(Integer.valueOf(10), list.get(20));
    }

    @Test
    public void testRemoveFirst() {
        LinkedList<Integer> list = newList();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.removeFirst();
        System.out.println(list);
        Assert.assertEquals(Integer.valueOf(1), list.get(0));
    }

    @Test
    public void testRemoveLast() {
        LinkedList<Integer> list= newList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.removeLast();
        System.out.println(list);
        Assert.assertEquals(9, list.size());
        Assert.assertEquals(Integer.valueOf(8), list.get(8));
    }

    @Test
    public void testIterator() {
        LinkedList<Integer> list = newList();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
