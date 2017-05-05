package com.johnChnia.coding2017.basic;

import org.junit.Before;
import org.junit.Test;
import com.johnChnia.coding2017.basic.linklist.LinkedList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by john on 2017/3/9.
 */
public class LinkedListTest {

    private LinkedList<Integer> linkList1;
    private LinkedList<Integer> linkList2;
    private LinkedList<Integer> linkList3;
    private LinkedList<Integer> linkList4;
    private LinkedList<Integer> linkList5;
    private LinkedList<Integer> linkList6;
    private LinkedList<Integer> linkList7;
    private LinkedList<Integer> linkList8;
    private LinkedList<Integer> linkList9;
    private LinkedList<Integer> linkList10;
    private LinkedList<Integer> linkList11;
    private LinkedList<Integer> linkList12;
    private LinkedList<Integer> linkList13;
    private LinkedList<Integer> linkList14;
    private LinkedList<Integer> linkList15;
    private LinkedList<Integer> linkList16;
    private LinkedList<Integer> linkList17;
    private LinkedList<Integer> linkList18;

    @Before
    public void setUp() throws Exception {
        linkList1 = new LinkedList<>();
        linkList2 = new LinkedList<>();
        linkList3 = new LinkedList<>();
        linkList4 = new LinkedList<>();
        linkList5 = new LinkedList<>();
        linkList6 = new LinkedList<>();
        linkList7 = new LinkedList<>();
        linkList8 = new LinkedList<>();
        linkList9 = new LinkedList<>();
        linkList10 = new LinkedList<>();
        linkList11 = new LinkedList<>();
        linkList12 = new LinkedList<>();
        linkList13 = new LinkedList<>();
        linkList14 = new LinkedList<>();
        linkList15 = new LinkedList<>();
        linkList16 = new LinkedList<>();
        linkList17 = new LinkedList<>();
        linkList18 = new LinkedList<>();
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
    public void testRemoveLast() {
        for (int i = 0; i < 2; i++) {
            linkList4.addFirst(i);
        }
        linkList4.removeLast();
        linkList4.removeLast();
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

    @Test
    public void testReverse() {
        linkList7.add(3);
        linkList7.add(7);
        linkList7.add(10);
        linkList7.reverse();
        assertThat(linkList7.toString(), containsString("10→7→3"));
    }

    @Test
    public void testRemoveFirstHalf() {
        linkList8.add(2);
        linkList8.add(5);
        linkList8.add(7);
        linkList8.add(8);
        linkList8.add(10);
        linkList8.removeFirstHalf();
        assertThat(linkList8.toString(), containsString("7→8→10"));
    }

    @Test
    public void testRemove() {
        for (int i = 1; i < 6; i++) {
            linkList9.add(i);
        }
        linkList9.remove(0, 4);
        assertThat(linkList9.toString(), containsString("5"));
    }

    @Test
    public void testRemoveDuplicateValues() {
        linkList11.add(1);
        linkList11.add(1);
        linkList11.add(1);
        linkList11.add(2);
        linkList11.add(3);
        linkList11.add(3);
        linkList11.removeDuplicateValues();
        assertThat(linkList11.toString(), containsString("1→2→3"));
    }

    @Test
    public void testRemoveRange() {
        linkList10.add(1);
        linkList10.add(1);
        linkList10.add(2);
        linkList10.add(3);
        linkList10.add(4);
        linkList10.add(5);
        linkList10.add(6);
        linkList10.removeRange(1, 4);
        assertThat(linkList10.toString(), containsString("4→5→6"));
    }

    @Test
    public void testIntersection() {
        for (int i = 1; i < 6; i++) {
            linkList12.add(i);
        }
        for (int i = 3; i < 9; i++) {
            linkList13.add(i);
        }
        assertThat(linkList12.intersection(linkList13).toString()
                , containsString("3→4→5"));
    }

    @Test
    public void testGetElements() {
        linkList15.add(1);
        linkList15.add(3);
        linkList15.add(4);
        linkList15.add(6);
        linkList14.add(11);
        linkList14.add(101);
        linkList14.add(201);
        linkList14.add(301);
        linkList14.add(401);
        linkList14.add(501);
        linkList14.add(601);
        linkList14.add(701);
        linkList14.getElements(linkList15);
        assertThat(Arrays.toString(linkList14.getElements(linkList15))
                , containsString("[101, 301, 401, 601]"));
    }

    @Test
    public void testSubtract() {
        for (int i = 1; i < 5; i++) {
            linkList17.add(i);
        }
        for (int i = 1; i < 4; i++) {
            linkList18.add(i);
        }
        linkList17.subtract(linkList18);
        assertThat(linkList17.toString()
                , containsString("4"));

    }

}