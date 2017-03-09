package com.coding.basic;

import org.junit.Test;

/**
 * Created by yrs on 2017/3/6.
 */
public class SingleLinkedListTest {

    @Test
    public void testAdd() throws Exception {

    }

    @Test
    public void testAdd1() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testRemove() throws Exception {

    }

    @Test
    public void testSize() throws Exception {

    }

    @Test
    public void testAddFirst() throws Exception {

    }

    @Test
    public void testAddLast() throws Exception {

    }

    @Test
    public void testRemoveFirst() throws Exception {

    }

    @Test
    public void testRemoveLast() throws Exception {

    }

    @Test
    public void testIterator() throws Exception {

    }

    @Test
    public void testReverse() throws Exception {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(3);
        singleLinkedList.add(2);
        singleLinkedList.add(1);
        singleLinkedList.add(0);
        singleLinkedList.reverse();
        for (int i=3; i>=0; i--) {
            assert (i == (int)singleLinkedList.get(i));
        }
    }

    @Test
    public void testRemoveFirstHalf() throws Exception {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(2);
        singleLinkedList.add(5);
        singleLinkedList.add(7);
        singleLinkedList.add(8);
        singleLinkedList.add(10);
        singleLinkedList.removeFirstHalf();
        assert (7 == (int)singleLinkedList.get(0));
        singleLinkedList.add(11);
        singleLinkedList.removeFirstHalf();
        assert (10 == (int)singleLinkedList.get(0));
    }

    @Test
    public void testRemove1() throws Exception {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(0);
        singleLinkedList.add(1);
        singleLinkedList.add(2);
        singleLinkedList.add(3);
        singleLinkedList.add(4);
        singleLinkedList.remove(0, 2);
        assert (2 == (int)singleLinkedList.get(0));
        singleLinkedList.remove(1, 1);
        assert (4 == (int)singleLinkedList.get(1));
    }

    @Test
    public void testGetElements() throws Exception {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(0);
        singleLinkedList.add(3);
        singleLinkedList.add(6);
        singleLinkedList.add(9);
        singleLinkedList.add(11);
        int[] array = {3, 9, 11};
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(3);
        list.add(4);
        int[] result = singleLinkedList.getElements(list);
        for(int i=0; i<3; i++) {
            assert (array[i] == result[i]);
        }
    }

    @Test
    public void testSubtract() throws Exception {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(7);
        singleLinkedList.add(3);
        singleLinkedList.add(5);
        singleLinkedList.add(9);
        singleLinkedList.add(11);
        SingleLinkedList list = new SingleLinkedList();
        list.add(1);
        list.add(3);
        list.add(7);
        singleLinkedList.subtract(list);
        assert (3 == singleLinkedList.size());
        assert (5 == (int)singleLinkedList.get(0));
    }

    @Test
    public void testRemoveDuplicateValues() throws Exception {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(1);
        singleLinkedList.add(3);
        singleLinkedList.add(9);
        singleLinkedList.add(9);
        singleLinkedList.add(9);
        singleLinkedList.add(11);
        singleLinkedList.removeDuplicateValues();
        assert (4 == singleLinkedList.size());
        assert (3 == (int)singleLinkedList.get(1));
        assert (9 == (int)singleLinkedList.get(2));
    }

    @Test
    public void testRemoveRange() throws Exception {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(1);
        singleLinkedList.add(3);
        singleLinkedList.add(5);
        singleLinkedList.add(9);
        singleLinkedList.add(9);
        singleLinkedList.add(11);
        singleLinkedList.add(12);
        singleLinkedList.removeRange(5, 12);
        assert (4 == singleLinkedList.size());
        assert (5 == (int)singleLinkedList.get(2));
    }

    @Test
    public void testIntersection() throws Exception {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(1);
        singleLinkedList.add(3);
        singleLinkedList.add(5);
        singleLinkedList.add(9);
        singleLinkedList.add(11);
        SingleLinkedList list = new SingleLinkedList();
        list.add(3);
        list.add(9);
        SingleLinkedList result = singleLinkedList.intersection(list);
        assert (3 == (int)result.get(0));
        assert (9 == (int)result.get(1));

    }
}