package com.johnChnia.coding2017.basic.test;

import com.johnChnia.coding2017.basic.LinkedList;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * Created by john on 2017/3/9.
 */
public class LinkedListTest {

    private LinkedList linkList1;
    private LinkedList linkList2;
    private LinkedList linkList3;
    private LinkedList linkList4;
    private LinkedList linkList5;
    private LinkedList linkList6;

    @Before
    public void setUp() throws Exception {
        linkList1 = new LinkedList();
        linkList2 = new LinkedList();
        linkList3 = new LinkedList();
        linkList4 = new LinkedList();
        linkList5 = new LinkedList();
        linkList6 = new LinkedList();
    }

    @Test
    public void testAddAndGet() {
        for (int i = 0; i < 4; i++) {
            linkList1.add(i);
        }
        assertThat(linkList1.get(0), equalTo(0));
        assertThat(linkList1.get(1), equalTo(1));
        assertThat(linkList1.get(2), equalTo(2));
    }

    @Test
    public void testSize() {
        for (int i = 0; i < 4; i++) {
            linkList3.add(i);
        }
        assertThat(linkList3.size(), equalTo(4));
    }

    @Test
    public void testAddFirst() {
        for (int i = 0; i < 4; i++) {
            linkList2.addFirst(i);
        }
        assertThat(linkList2.get(0), equalTo(3));
        assertThat(linkList2.get(1), equalTo(2));
    }

    @Test
    public void testRemove() {
        for (int i = 0; i < 2; i++) {
            linkList4.addFirst(i);
        }
        linkList4.remove();
        linkList4.remove();
        assertThat(linkList4.size(), equalTo(0));
    }


    @Test
    public void testAddByIndex() {
        for (int i = 0; i < 2; i++) {
            linkList5.add(i);
        }
        linkList5.add(0, 100);
        linkList5.add(1, 1000);
        System.out.println(linkList5);
        assertThat(linkList5.get(1), equalTo(1000));
    }

    @Test
    public void testRemoveFirst() {
        for (int i = 0; i < 4; i++) {
            linkList6.addFirst(i);
        }
        linkList6.removeFirst();
        linkList6.removeFirst();
        linkList6.removeFirst();
        assertThat(linkList6.get(0), equalTo(0));
    }

}