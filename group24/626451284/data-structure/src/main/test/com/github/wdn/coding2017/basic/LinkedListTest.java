package com.github.wdn.coding2017.basic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/4/3 0003.
 */
public class LinkedListTest {
    LinkedList linkedList = new LinkedList();
    @Before
    public void initLinkedList(){
        for (int i = 0; i < 12; i++) {
            linkedList.add(i);
        }
    }
    @Test
    public void testReverse(){

        System.out.println(linkedList.size());
        System.out.println(linkedList);
        linkedList.reverse();
        System.out.println("");
        System.out.println(linkedList);
    }
    @Test
    public void testRemoveFirstHalf(){
        System.out.println(linkedList);
        linkedList.removeFirstHalf();
        System.out.println(linkedList);
    }
    @Test
    public void testRemove(){
        System.out.println(linkedList);
        //linkedList.remove(0,30);
        //System.out.println(linkedList);
        //linkedList.remove(2,30);
        //System.out.println(linkedList);
        linkedList.remove(2,0);
        System.out.println(linkedList);
    }
    @Test
    public void testGetElements(){
        LinkedList indexs = new LinkedList();
        indexs.add(3);
        indexs.add(5);
        indexs.add(7);
        indexs.add(9);
        int[] result = linkedList.getElements(indexs);
        System.out.println(Arrays.toString(result));
        Assert.assertArrayEquals(new int[]{3, 5, 7, 9},result);
    }
    @Test
    public void testSubtract(){
        LinkedList indexs = new LinkedList();
        indexs.add(3);
        indexs.add(5);
        indexs.add(7);
        indexs.add(9);
        linkedList.subtract(indexs);
        System.out.println(linkedList);
        System.out.println(linkedList.size());
    }
    @Test
    public void testRemoveDuplicateValues(){
        LinkedList list = new LinkedList();
        list.add(3);
        //list.add(3);
        list.add(5);
        //list.add(5);
        list.add(7);
        list.add(7);
        list.add(9);
        list.add(9);
        list.removeDuplicateValues();
        System.out.println(list);
    }
    @Test
    public void testRemoveRange(){
        LinkedList indexs = new LinkedList();
        indexs.add(3);
        indexs.add(5);
        indexs.add(7);
        indexs.add(9);
        indexs.removeRange(9, 9);
        System.out.println(indexs);
    }
}
