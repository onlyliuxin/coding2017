package com.byhieg.coding2017.homework312;

import junit.framework.TestCase;

/**
 * Created by byhieg on 17/3/8.
 * Mail to byhieg@gmail.com
 */
public class LinkedListTest extends TestCase {

    private LinkedList list = new LinkedList();
    public void testReverse() throws Exception {
        System.out.println("Reverse开始");
        list.add(3);
        list.add(1,7);
        list.add(2,8);
        list.add(10);
        list.add(11);
        for (int i = 0 ; i < list.size();i++) {
            System.out.print(list.get(i) + " ");
        }

        list.reverse();
        System.out.println();
        for (int i = 0 ; i < list.size();i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        System.out.println("Reverse结束");
    }

    public void testRemoveFirstHalf() throws Exception {
        System.out.println("RemoveFirstHalf开始");
        list.add(3);
        list.add(1,7);
        list.add(2,8);
        list.add(10);
        list.add(11);
        list.removeFirstHalf();
        for (int i = 0 ; i < list.size();i++) {
            System.out.println(list.get(i) + " ");
        }
        System.out.println("RemoveFirstHalf结束");

    }

    public void testRemove() throws Exception {
        System.out.println("remove开始");
        list.add(3);
        list.add(7);
        list.add(8);
        list.add(10);
        list.add(11);
        list.remove(0,3);
        for (int i = 0 ; i < list.size();i++) {
            System.out.println(list.get(i) + " ");
        }
        System.out.println("remove结束");
    }

    public void testGetElements() throws Exception {
        System.out.println("getElements开始");
        list.add(3);
        list.add(7);
        list.add(8);
        list.add(10);
        list.add(11);
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(3);
        int [] result = list.getElements(linkedList);
        for (int i = 0 ; i < result.length;i++) {
            System.out.println(result[i] + " ");
        }
        System.out.println("getElements结束");
    }

    public void testSubtract() throws Exception {
        System.out.println("subtract开始");
        list.add(3);
        list.add(7);
        list.add(8);
        list.add(10);
        list.add(11);
        LinkedList linkedList = new LinkedList();
        linkedList.add(8);
        linkedList.add(3);
        list.subtract(linkedList);
        for (int i = 0 ; i < list.size();i++) {
            System.out.println(list.get(i) + " ");
        }
        System.out.println("subtract结束");
    }

    public void testRemoveDuplicateValues() throws Exception {
        System.out.println("RemoveDuplicateValues开始");
        list.add(3);
        list.add(8);
        list.add(8);
        list.add(10);
        list.add(10);
        list.removeDuplicateValues();
        for (int i = 0 ; i < list.size();i++) {
            System.out.println(list.get(i) + " ");
        }
        System.out.println("RemoveDuplicateValues结束");
    }

    public void testRemoveRange() throws Exception {
        System.out.println("RemoveRange开始");
        list.add(3);
        list.add(5);
        list.add(8);
        list.add(10);
        list.add(101);
        list.removeRange(4,9);
        for (int i = 0 ; i < list.size();i++) {
            System.out.println(list.get(i) + " ");
        }
        System.out.println("RemoveRange结束");
    }

    public void testIntersection() throws Exception {
        System.out.println("Intersection开始");
        list.add(3);
        list.add(5);
        list.add(8);
        list.add(10);
        list.add(101);
        LinkedList b = new LinkedList();
        b.add(5);
        b.add(8);
        b.add(10);
        b.add(123);
        LinkedList c = list.intersection(b);
        for (int i = 0 ; i < c.size();i++) {
            System.out.println(c.get(i) + " ");
        }
        System.out.println("Intersection结束");
    }

}