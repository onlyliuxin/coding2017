package com.coding2017.week3;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Created by kaitao.li on 2017/3/11.
 */
public class LinkedListTest {

    @Test
    public void testReverse() {
        LinkedList list = mockEmptyList();
        list.reverse();
        assertTrue(list.size() == 0);

        list = mockOneNodeList();
        list.reverse();
        assertTrue(list.get(0).equals(1));
        assertTrue(list.size() == 1);

        list = mockThreeNodeList();
        list.reverse();
        assertTrue(list.size() == 3);
        assertTrue(list.get(0).equals(3));
        assertTrue(list.get(1).equals(2));
        assertTrue(list.get(2).equals(1));
    }

    @Test
    public void testRemoveFirstHalf() {
        LinkedList list = new LinkedList();
        list.add(2);
        list.add(5);
        list.add(7);
        list.add(8);
        list.removeFirstHalf();
        assertTrue(list.size() == 2);
        assertTrue(list.get(0).equals(7));
        assertTrue(list.get(1).equals(8));

        list = new LinkedList();
        list.add(2);
        list.add(5);
        list.add(7);
        list.removeFirstHalf();
        assertTrue(list.size() == 2);
        assertTrue(list.get(0).equals(5));
        assertTrue(list.get(1).equals(7));
    }

    @Test
    public void testRemove() {
        LinkedList linkedList = mockThreeNodeList();
        linkedList.remove(1, 1);
        assertTrue(linkedList.size() == 2);
        assertTrue(linkedList.get(0).equals(1));
        assertTrue(linkedList.get(1).equals(3));
    }

    @Test
    public void testGetElements() {
        LinkedList linkedList = LinkedList.of(11, 101, 201, 301, 401, 501, 601, 701);
        LinkedList indexList = LinkedList.of(1, 3, 4, 6);
        int[] elements = linkedList.getElements(indexList);
        assertArrayEquals(elements, new int[] { 101, 301, 401, 601 });
    }

    @Test
    public void testSubtract() {
        LinkedList linkedList = LinkedList.of(11, 101, 201, 301, 401, 501, 601, 701);
        LinkedList deleteList = LinkedList.of(11, 201, 301, 601, 701);
        linkedList.subtract(deleteList);
        assertTrue(linkedList.size() == 3);
        assertTrue(linkedList.get(0).equals(101));
        assertTrue(linkedList.get(1).equals(401));
        assertTrue(linkedList.get(2).equals(501));
    }

    @Test
    public void testRemoveDuplicateValues() {
        LinkedList linkedList = LinkedList.of(11, 101, 101, 301, 401, 401, 401, 701);
        linkedList.removeDuplicateValues();
        assertTrue(linkedList.size() == 5);
        assertTrue(linkedList.get(0).equals(11));
        assertTrue(linkedList.get(1).equals(101));
        assertTrue(linkedList.get(2).equals(301));
        assertTrue(linkedList.get(3).equals(401));
        assertTrue(linkedList.get(4).equals(701));
    }

    @Test
    public void testRemoveRange() {
        LinkedList linkedList = LinkedList.of(11, 101, 201, 301, 401, 501, 601, 701);
        linkedList.removeRange(10, 601);
        assertTrue(linkedList.size() == 2);
        assertTrue(linkedList.get(0).equals(601));
        assertTrue(linkedList.get(1).equals(701));
    }

    @Test
    public void testIntersection() {
        LinkedList linkedList = LinkedList.of(1, 2, 3, 4);
        LinkedList linkedList1 = LinkedList.of(2, 4, 6, 7);
        LinkedList result = linkedList.intersection(linkedList1);
        assertTrue(result.size() == 2);
        assertTrue(result.get(0).equals(2));
        assertTrue(result.get(1).equals(4));
    }

    private LinkedList mockEmptyList() {
        return new LinkedList();
    }

    private LinkedList mockOneNodeList() {
        LinkedList list = new LinkedList();
        list.add(1);
        return list;
    }

    private LinkedList mockThreeNodeList() {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        return list;
    }

}