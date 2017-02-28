package org.korben.list;

import java.util.Objects;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * KList测试
 *
 * Created by Korben on 18/02/2017.
 */
public class KListTest {

    private KList<Integer> list;

    private int initTestSize;

    @Before
    public void init() {
        // 测试KArrayList
        //list = new KArrayList<>();

        // 测试KLinkedList
        list = new KLinkedList<>();

        initTestSize = 5;

        for (int i = 0; i < initTestSize; i++) {
            list.add(i);
        }
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(initTestSize, list.size());
    }

    @Test
    public void isEmpty() throws Exception {
        Assert.assertFalse(list.isEmpty());

        KList<String> list = new KArrayList<>();
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void contains() throws Exception {
        Assert.assertTrue(list.contains(1));
        Assert.assertFalse(list.contains(5));
    }

    @Test
    public void toArray() throws Exception {
        //Object[] array = list.toArray();
        //Assert.assertEquals(initTestSize, array.length);
        //for (int i = 0; i < array.length; i++) {
        //    Assert.assertEquals(i, array[i]);
        //}
    }

    @Test
    public void add() throws Exception {
        Assert.assertTrue(list.add(6));
        Assert.assertTrue(list.contains(6));
        Assert.assertEquals(initTestSize + 1, list.size());
    }

    @Test
    public void remove() throws Exception {
        Assert.assertEquals(0, list.remove(0).intValue());
        Assert.assertEquals(initTestSize - 1, list.size());
        Assert.assertFalse(list.contains(0));
    }

    @Test
    public void clear() throws Exception {
        list.clear();
        Assert.assertTrue(list.isEmpty());
        Assert.assertTrue(list.size() == 0);
    }

    @Test
    public void get() throws Exception {
        for (int i = 0; i < initTestSize; i++) {
            Assert.assertTrue(Objects.equals(i, list.get(i)));
        }
    }

    @Test
    public void set() throws Exception {
        for (int i = 0; i < initTestSize; i++) {
            list.set(i, initTestSize);
            Assert.assertEquals(initTestSize, list.get(i).intValue());
        }
    }

    @Test
    public void addByIndex() throws Exception {
        // test add by first index
        list.add(0, 6);
        Assert.assertEquals(initTestSize + 1, list.size());
        Assert.assertEquals(6, list.get(0).intValue());
        for (int i = 0; i < initTestSize; i++) {
            Assert.assertEquals(i, list.get(i + 1).intValue());
        }

        // test add by last index
        init();
        list.add(initTestSize - 1, 6);
        Assert.assertEquals(initTestSize + 1, list.size());
        Assert.assertEquals(initTestSize - 1, list.get(initTestSize).intValue());
        Assert.assertEquals(6, list.get(initTestSize - 1).intValue());
        for (int i = 0; i < initTestSize - 1; i++) {
            Assert.assertEquals(i, list.get(i).intValue());
        }

        // test add by middle index
        init();
        list.add(3, 90);
        Assert.assertEquals(initTestSize + 1, list.size());
        Assert.assertEquals(list.get(0).intValue(), 0);
        Assert.assertEquals(list.get(1).intValue(), 1);
        Assert.assertEquals(list.get(2).intValue(), 2);
        Assert.assertEquals(list.get(3).intValue(), 90);
        Assert.assertEquals(list.get(4).intValue(), 3);
        Assert.assertEquals(list.get(5).intValue(), 4);
    }

    @Test
    public void removeByObject() throws Exception {
        Assert.assertTrue(list.remove(new Integer(3)));
        Assert.assertEquals(initTestSize - 1, list.size());
    }

    @Test
    public void indexOf() throws Exception {
        for (int i = 0; i < initTestSize; i++) {
            Assert.assertEquals(i, list.indexOf(i));
        }
    }
}
