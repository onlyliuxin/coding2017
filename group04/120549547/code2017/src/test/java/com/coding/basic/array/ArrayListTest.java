package com.coding.basic.array;

import com.coding.basic.Iterator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bobi on 2017/4/1.
 * at code2017
 */
public class ArrayListTest {

    private  ArrayList<Integer> arrayList;
    @Before
    public void init() {
        arrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arrayList.add(i);
        }
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(5, arrayList.size());
        arrayList.add(6);
        Assert.assertEquals(6, arrayList.size());

    }

    @Test
    public void isEmpty() throws Exception {
        arrayList.clear();
        Assert.assertEquals(0, arrayList.size());
        Assert.assertTrue(arrayList.isEmpty());
    }

    @Test
    public void contains() throws Exception {
        Assert.assertTrue(arrayList.contains(0));
        Assert.assertTrue(arrayList.contains(4));
        Assert.assertFalse(arrayList.contains(5));
    }

    @Test
    public void toArray() throws Exception {
        Integer[] integers = new Integer[]{0, 1, 2, 3, 4};
        Assert.assertArrayEquals(integers, arrayList.toArray());
    }

    @Test
    public void add() throws Exception {
        arrayList.add(5);
        Assert.assertEquals(5, arrayList.get(arrayList.size() - 1).intValue());
        arrayList.add(0, 6);
        arrayList.add(3, 7);
        arrayList.add(arrayList.size(), 8);

        Assert.assertEquals(9, arrayList.size());
        Assert.assertEquals(6, arrayList.get(0).intValue());
        Assert.assertEquals(8, arrayList.get(arrayList.size()-1).intValue());
        Assert.assertEquals(7, arrayList.get(3).intValue());
    }

    @Test
    public void remove() throws Exception {
        arrayList.remove(0);
        arrayList.remove(3);
        arrayList.add(5);
        Assert.assertArrayEquals(arrayList.toArray(), new Integer[]{1,2,3,5});

        arrayList.remove(new Integer(1));
        arrayList.remove(new Integer(2));
        arrayList.remove(new Integer(5));
        arrayList.add(6);
        arrayList.add(7);

        Assert.assertArrayEquals(arrayList.toArray(), new Integer[]{3,6,7});

    }



    @Test
    public void get() throws Exception {
        Assert.assertEquals(0, arrayList.get(0).intValue());
        Assert.assertEquals(3, arrayList.get(3).intValue());
        arrayList.add(5);
        arrayList.remove(0);
        Assert.assertEquals(5, arrayList.get(4).intValue());
    }

    @Test
    public void set() throws Exception {
        arrayList.set(0,100);
        arrayList.set(arrayList.size() - 1, 50);
        Assert.assertEquals(100, arrayList.get(0).intValue());
        Assert.assertEquals(50, arrayList.get(arrayList.size() - 1).intValue());

    }





    @Test
    public void indexOf() throws Exception {
        Assert.assertEquals(0, arrayList.indexOf(0));
        Assert.assertEquals(1, arrayList.indexOf(1));
    }

    @Test
    public void iterator() throws Exception {
        Iterator iterator = arrayList.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(0, iterator.next());
        Assert.assertEquals(1, iterator.next());
    }

}