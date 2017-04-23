package com.coding.basic.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 链表测试--第三次算法作业
 *
 * Created by Korben on 07/03/2017.
 */
public class KLinkedListTest {

    private KLinkedList<Integer> linkedList;

    @Before
    public void init() {
        linkedList = new KLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
        }
    }

    @Test
    public void reverse() throws Exception {
        // 测试多个
        linkedList.reverse();
        Assert.assertEquals(linkedList.size(), 5);
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(linkedList.get(i).intValue(), 4 - i);
        }

        // 测试空链表
        linkedList = new KLinkedList<>();
        linkedList.reverse();
        Assert.assertEquals(linkedList.size(), 0);

        // 测试单个
        linkedList.add(0);
        linkedList.reverse();
        Assert.assertEquals(linkedList.size(), 1);
        Assert.assertEquals(linkedList.get(0).intValue(), 0);
    }

    @Test
    public void removeFirstHalf() throws Exception {
        linkedList.removeFirstHalf();
        Assert.assertEquals(linkedList.size(), 3);
        for (int i = 0; i < 3; i++) {
            Assert.assertEquals(linkedList.get(i).intValue(), i + 2);
        }

        linkedList = new KLinkedList<>();
        linkedList.removeFirstHalf();
        Assert.assertEquals(linkedList.size(), 0);
    }

    @Test
    public void remove() throws Exception {
        // 测试删除开始节点
        {
            linkedList.remove(0, 2);
            Assert.assertEquals(linkedList.size(), 3);
            for (int i = 0; i < 3; i++) {
                Assert.assertEquals(linkedList.get(i).intValue(), i + 2);
            }
        }

        // 测试删除中间节点
        {
            init();
            linkedList.remove(1, 2);
            Assert.assertEquals(linkedList.size(), 3);
            Assert.assertEquals(linkedList.get(0).intValue(), 0);
            Assert.assertEquals(linkedList.get(1).intValue(), 3);
            Assert.assertEquals(linkedList.get(2).intValue(), 4);
        }

        // 测试删除末尾节点
        {
            init();
            linkedList.remove(3, 2);
            Assert.assertEquals(linkedList.size(), 3);
            Assert.assertEquals(linkedList.get(0).intValue(), 0);
            Assert.assertEquals(linkedList.get(1).intValue(), 1);
            Assert.assertEquals(linkedList.get(2).intValue(), 2);
        }

        // 测试删除全部
        {
            init();
            linkedList.remove(0, 5);
            Assert.assertEquals(linkedList.size(), 0);
        }
    }

    @Test
    public void getElements() throws Exception {
        KLinkedList<Integer> list = new KLinkedList<>();
        list.add(2);
        list.add(4);

        int[] elements = linkedList.getElements(list);
        Assert.assertEquals(elements.length, 2);
        Assert.assertEquals(elements[0], linkedList.get(2).intValue());
        Assert.assertEquals(elements[1], linkedList.get(4).intValue());
    }

    @Test
    public void subtract() throws Exception {
        KLinkedList<Integer> list = new KLinkedList<>();
        list.add(2);
        list.add(4);

        linkedList.subtract(list);
        Assert.assertEquals(linkedList.size(), 3);
        Assert.assertEquals(linkedList.get(0).intValue(), 0);
        Assert.assertEquals(linkedList.get(1).intValue(), 1);
        Assert.assertEquals(linkedList.get(2).intValue(), 3);
    }

    @Test
    public void removeDuplicateValues() throws Exception {
        linkedList = new KLinkedList<>();
        for (int i = 0; i < 10; i++) {
            linkedList.add(i / 2);
        }

        linkedList.removeDuplicateValues();
        Assert.assertEquals(linkedList.size(), 5);
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(linkedList.get(i).intValue(), i);
        }
    }

    @Test
    public void removeRange() throws Exception {
        linkedList.removeRange(2, 4);
        Assert.assertEquals(linkedList.size(), 2);
        Assert.assertEquals(linkedList.get(0).intValue(), 0);
        Assert.assertEquals(linkedList.get(1).intValue(), 1);
    }

    @Test
    public void intersection() throws Exception {
        KLinkedList insertList = new KLinkedList();
        for (int i = 3; i < 8; i++) {
            insertList.add(i);
        }

        KLinkedList intersection = linkedList.intersection(insertList);
        Assert.assertEquals(intersection.size(), 8);
        for (int i = 0; i < intersection.size(); i++) {
            Assert.assertEquals(intersection.get(i), i);
        }
    }
}