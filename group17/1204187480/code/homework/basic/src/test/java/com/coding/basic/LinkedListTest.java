package com.coding.basic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by luoziyihao on 3/23/17.
 */
public class LinkedListTest {
    @Test
    public void iterator() throws Exception {

    }

    @Test
    public void reverse() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.reverse();
        System.out.println(linkedList);
    }

}