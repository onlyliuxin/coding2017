package com.circle.collection;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by keweiyang on 2017/3/14.
 */
public class LinkedListV2Test {

    private LinkedListV2 list = new LinkedListV2();

    @Test
    public void add() throws Exception {
        list.add(null);
        list.add(1);
        list.add(2);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }
    }

    @Test
    public void add1() throws Exception {
        list.add(0, 1);
        list.add(1, 3);
        list.add(2, 5);
        list.add(6);

        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }


    }

    @Test
    public void get() throws Exception {
        list.add(0, 1);
        list.add(1, 3);
        list.add(2, 5);
        list.add(6);
        Assert.assertEquals(6, list.get(2));

    }

    @Test
    public void size() throws Exception {
        list.add(0, 1);
        list.add(1, 3);
        list.add(2, 5);
        list.add(6);
        Assert.assertEquals(4, list.size());

    }

    @Test
    public void removeFirst() throws Exception {
        list.add(0, 1);
        list.add(1, 3);
        list.add(2, 5);
        list.add(6);

        Assert.assertEquals(1, list.removeFirst());

    }

    @Test
    public void removeLast() throws Exception {
        list.add(0, 1);
        list.add(1, 3);
        list.add(2, 5);
        list.add(6);

        Assert.assertEquals(6,list.removeLast());

    }

    @Test
    public void reverse() throws Exception {
        list.add(0, 3);
        list.add(1, 7);
        list.add(2, 10);

        list.reverse();

    }

    @Test
    public void removeFirstHalf() throws Exception {
        list.add(0, 2);
        list.add(1, 5);
        list.add(2, 7);
        list.add(3, 8);
        list.add(4, 10);
        list.removeFirstHalf();

    }

    @Test
    public void remove() throws Exception {
        list.add(0, 2);
        list.add(1, 5);
        list.add(2, 7);
        list.add(3, 8);
        list.add(4, 10);
        list.remove(2, 3);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }

    }

    @Test
    public void getElements() throws Exception {
        list.add(0, 11);
        list.add(1, 101);
        list.add(2, 201);
        list.add(3, 301);
        list.add(4, 401);
        list.add(5, 501);
        list.add(6, 601);

        LinkedListV2<Integer> listV2 = new LinkedListV2();
        listV2.add(0, 1);
        listV2.add(1, 3);
        listV2.add(2, 4);
        listV2.add(3, 6);

        Object[] results = list.getElements(listV2);
        System.out.println(results.length);

        assertEquals(4, results.length);

        for (Object object : results) {
            System.out.println(object);

        }

    }

    @Test
    public void toArray() throws Exception {

    }

    @Test
    public void subtract() throws Exception {
        list.add(0, 100);
        list.add(1, 101);
        list.add(2, 201);
        list.add(3, 301);
        list.add(4, 401);
        list.add(5, 501);
        list.add(6, 601);

        LinkedListV2<Integer> listV2 = new LinkedListV2();
        listV2.add(0, 100);
        listV2.add(1, 301);
        listV2.add(2, 401);
        listV2.add(3, 601);
        list.subtract(listV2);

        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }


    }

    @Test
    public void removeDuplicateValues() throws Exception {

        list.add(0, 100);
        list.add(1, 100);
        list.add(2, 201);
        list.add(3, 301);
        list.add(4, 401);
        list.add(5, 401);
        list.add(6, 401);
        list.removeDuplicateValues();

        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }
    }

    @Test
    public void removeRange() throws Exception {

        list.add(0, 100);
        list.add(1, 110);
        list.add(2, 201);
        list.add(3, 211);
        list.add(4, 151);
        list.add(5, 131);
        list.add(6, 171);
        list.removeRange(110, 150);

        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }

    }

    @Test
    public void intersection() throws Exception {
        list.add(0, 100);
        list.add(1, 110);
        list.add(2, 201);
        list.add(3, 211);
        list.add(4, 351);
        list.add(5, 431);

        LinkedListV2<Integer> listV2 = new LinkedListV2();
        listV2.add(0, 100);
        listV2.add(1, 351);
        listV2.add(2, 431);
        listV2.add(3, 600);

        LinkedListV2<Integer> integerLinkedListV2 = list.intersection(listV2);

        Iterator it = integerLinkedListV2.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }
    }


}