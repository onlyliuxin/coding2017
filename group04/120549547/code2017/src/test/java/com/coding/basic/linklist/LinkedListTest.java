package com.coding.basic.linklist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by bobi on 2017/3/31.
 * at code2017
 */
public class LinkedListTest {


    @Before
    public void init() {
        linkedList = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            linkedList.add(1<<i);
        }
    }
    @Test
    public void reverse() throws Exception {
        linkedList.reverse();
        linkedList.printf();
    }
    @Test
    public void contains() throws Exception {
        Assert.assertFalse(linkedList.contains(15));
        Assert.assertTrue(linkedList.contains(16));
    }

    @Test
    public void clear() throws Exception {
        linkedList.clear();
        Assert.assertTrue(linkedList.isEmpty());
        Assert.assertTrue(linkedList.size() == 0);
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(8, linkedList.get(3).intValue());
        linkedList.remove(3);
        Assert.assertEquals(16, linkedList.get(3).intValue());

        linkedList.set(3, 100);

        Assert.assertEquals(100, linkedList.get(3).intValue());

    }





    private LinkedList<Integer> linkedList;

    @Test
    public void remove() throws Exception {
        //测试添加删除
        {
            linkedList.printf();
            linkedList.remove(new Integer(1));
            linkedList.remove(new Integer(8));
            linkedList.remove(new Integer(32));
            linkedList.add(50);
            linkedList.add(0, 100);
            Assert.assertArrayEquals(linkedList.toArray(), new Integer[]{ 100, 2, 4, 16, 50});

//
            linkedList.remove(new Integer(16));
            linkedList.add(linkedList.size() - 1, 25);
            Assert.assertArrayEquals(linkedList.toArray(), new Integer[]{ 100,2, 4, 25,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             50});
        }

    }


    @Test
    public void removeFirstHalf() throws Exception {
        linkedList.removeFirstHalf();
        linkedList.removeFirstHalf();
        linkedList.printf();
    }

    @Test
    public void getElements() throws Exception {

    }

    @Test
    public void subtract() throws Exception {

    }

    @Test
    public void removeDuplicateValues() throws Exception {
        linkedList.removeFirstHalf();
        LinkedList list = new LinkedList<Integer>();
        list.add(8);
        list.add(16);
        list.add(32);
        Assert.assertArrayEquals(linkedList.toArray(), list.toArray());
        linkedList.removeFirstHalf();
        list.remove(0);
        Assert.assertArrayEquals(linkedList.toArray(), list.toArray());

    }

    @Test
    public void removeByLength() throws Exception {
        // 测试删除开始节点
        {
            linkedList.remove(0, 2);
            Assert.assertEquals(linkedList.size(), 4);
            for (int i = 0; i < 3; i++) {
                Assert.assertEquals(linkedList.get(i).intValue(), 1<<(i+2));
            }
        }

        // 测试删除中间节点
        {
            init();
            linkedList.remove(1, 2);
            Assert.assertEquals(linkedList.size(), 4);
            Assert.assertEquals(linkedList.get(0).intValue(), 1);
            Assert.assertEquals(linkedList.get(1).intValue(), 8);
            Assert.assertEquals(linkedList.get(2).intValue(), 16);
        }
//
        // 测试删除末尾节点
        {
            init();
            linkedList.remove(4, 2);
            Assert.assertEquals(linkedList.size(), 4);
            Assert.assertEquals(linkedList.get(0).intValue(), 1);
            Assert.assertEquals(linkedList.get(1).intValue(), 2);
            Assert.assertEquals(linkedList.get(2).intValue(), 4);
            Assert.assertEquals(linkedList.get(3).intValue(), 8);
        }
//
        // 测试删除全部
        {
            init();
            linkedList.remove(0, 6);
            Assert.assertEquals(linkedList.size(), 0);
        }
    }

    @Test
    public void intersection() throws Exception {

    }

}