package com.coding.basic;

import org.junit.Assert;
import org.junit.Test;

public class ArrayListTest {

    @Test
    public void add() throws Exception {
        ArrayList data;
        int size;
        //size < 100
        data = new ArrayList();
        size = 33;
        for (int i = 0; i < size; i++) {
            data.add(i);
        }
        for (int i = 0; i < size; i++) {
            Assert.assertEquals(i, data.get(i));
        }
        //size > 100
        data = new ArrayList();
        size = 333;
        for (int i = 0; i < size; i++) {
            data.add(i);
        }
        for (int i = 0; i < size; i++) {
            Assert.assertEquals(i, data.get(i));
        }
        //size = 100
        data = new ArrayList();
        size = 100;
        for (int i = 0; i < size; i++) {
            data.add(i);
        }
        for (int i = 0; i < size; i++) {
            Assert.assertEquals(i, data.get(i));
        }
    }

    @Test
    public void add1() throws Exception {
        ArrayList data;
        int size;
        int index;
        boolean b;
        // size < 100;
        data = new ArrayList();
        size = 5;
        index = 2;
        b = false;
        for (int i = 0; i < size; i++) {
            data.add(i);
        }
        data.add(index, index + 10000);
        for (int i = 0; i < data.size(); i++) {
            if (i == index) {
                b = true;
                Assert.assertEquals(index + 10000, data.get(i));
            }
            else {
                if (b) {
                    Assert.assertEquals(i - 1, data.get(i));
                }
                else {
                    Assert.assertEquals(i, data.get(i));
                }
            }
        }
    }

    @Test
    public void get() throws Exception {
        ArrayList data = new ArrayList();
        data.add(1);
        data.add(2);
        data.add(3);
        Assert.assertEquals(2, data.get(1));
    }

    @Test
    public void remove() throws Exception {
        ArrayList data = new ArrayList();
        data.add(1);
        data.add(2);
        data.add(3);
        data.remove(1);
        Assert.assertEquals(2, data.size());
        Assert.assertEquals(1, data.get(0));
        Assert.assertEquals(3, data.get(1));
    }

    @Test
    public void size() throws Exception {
        ArrayList data = new ArrayList();
        data.add(1);
        data.add(1);
        data.add(1);
        Assert.assertEquals(3, data.size());
    }

    @Test
    public void iterator() throws Exception {
        ArrayList data = new ArrayList();
        data.add(0);
        data.add(1);
        data.add(2);
        Iterator iterator = data.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Assert.assertEquals(++i, iterator.next());
        }
    }
}