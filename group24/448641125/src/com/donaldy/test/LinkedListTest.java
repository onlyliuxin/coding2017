package com.donaldy.test;

import com.donaldy.basic.LinkedList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by donal on 2017/3/9.
 */
public class LinkedListTest {

    private LinkedList linkedList;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void before() throws Exception {
        linkedList = new LinkedList();
        for (String s : "GEGE lala haha momo dada!".split(" "))
            linkedList.add(s);
    }

    @Test
    public void testAddWithArg() {
        assertEquals("GEGE", linkedList.get(0));
        assertEquals("dada!", linkedList.get(4));

    }

    @Test
    public void testAddWithArgs() {
        String str = "kunkun";
        linkedList.add(3, str);
        assertEquals(str, linkedList.get(3));
        linkedList.add(0, str);
        assertEquals(str, linkedList.get(0));
    }

    @Test
    public void testRuntimeException() {
        thrown.expect(RuntimeException.class);
        linkedList.get(10);
    }

    @Test
    public void testRuntimeException2() {
        thrown.expect(IndexOutOfBoundsException.class);
        linkedList.get(-1);
    }

    @Test
    public void testRemove() {
        assertEquals("GEGE", linkedList.remove(0));
        assertEquals("dada!", linkedList.remove(3));
        assertEquals("haha", linkedList.remove(1));

    }

    @Test
    public void testRuntimeExceptionByremove() {
        thrown.expect(IndexOutOfBoundsException.class);
        LinkedList linkedList2 = new LinkedList();
        linkedList2.remove(0);
    }

    @Test
    public  void testSize() {
        assertEquals(5, linkedList.size());
    }

    @Test
    public void testReverse() {
        linkedList.reverse();
        assertEquals("GEGE", linkedList.get(4));
        assertEquals("lala", linkedList.get(3));
        assertEquals("haha", linkedList.get(2));
        assertEquals("momo", linkedList.get(1));
        assertEquals("dada!", linkedList.get(0));
    }

    @Test
    public void testRemoveFirstHalf() {
        linkedList.removeFirstHalf();
        assertEquals("haha", linkedList.get(0));
        assertEquals("momo", linkedList.get(1));
        assertEquals("dada!", linkedList.get(2));
    }

    @Test
    public void testRemoveWithArgs() {
        linkedList.remove(1, 2);
        assertEquals("GEGE", linkedList.get(0));
        assertEquals("momo", linkedList.get(1));
        assertEquals("dada!", linkedList.get(2));
    }

    @Test
    public void testRemoveWithArgs2() {
        linkedList.remove(0, 2);
        assertEquals("haha", linkedList.get(0));
        assertEquals("momo", linkedList.get(1));
        assertEquals("dada!", linkedList.get(2));
    }

    @Test
    public void testRemoveWithArgs3() {
        linkedList.remove(4, 1);
        int size = linkedList.size();
        System.out.println();
        assertEquals("momo", linkedList.get(size - 1));
    }

    @Test
    public void testGetElements() {
        LinkedList link = new LinkedList();
        LinkedList linkB = new LinkedList();
        int test[] = {101, 301, 401, 601};
        link.add(11);
        link.add(101);
        link.add(201);
        link.add(301);
        link.add(401);
        link.add(501);
        link.add(601);
        link.add(701);
        linkB.add(1);
        linkB.add(3);
        linkB.add(4);
        linkB.add(6);
        assertArrayEquals(test, link.getElements(linkB));
    }

    @Test
    public void testSubtract() {
        LinkedList link = new LinkedList();
        LinkedList linkB = new LinkedList();
        for (int i = 0; i < 5; ++i)
            linkB.add(i);
        for (int i = 0; i < 10; ++i)
            link.add(i);
        link.subtract(linkB);
        assertEquals(5, link.size());
        assertEquals(5, link.get(0));
        assertEquals(6, link.get(1));
        assertEquals(7, link.get(2));
        assertEquals(8, link.get(3));
        assertEquals(9, link.get(4));
    }

    @Test
    public void testRemoveDuplicateValues() {
        linkedList.add("GEGE");
        linkedList.add("haha");
        linkedList.add("dada!");
        linkedList.removeDuplicateValues();
        assertEquals(5, linkedList.size());
        assertEquals("GEGE", linkedList.get(0));
        assertEquals("haha", linkedList.get(2));
        assertEquals("dada!", linkedList.get(4));
    }

    @Test
    public void testRemoveRange() {
        LinkedList link = new LinkedList();
        for (int i = 10; i < 30; ++i) {
            link.add(i);
        }
        link.removeRange(15, 19);
        assertEquals(17, link.size());
        link.removeRange(9, 13);
        assertEquals(14, link.size());
        assertEquals(13, (int)link.get(0));
        link.removeRange(26, 30);
        assertEquals(11, link.size());
        assertEquals(26, (int)link.get(10));
    }

    @Test
    public void testIntersection() {
        LinkedList link = new LinkedList();
        LinkedList linkB = new LinkedList();
        for (int i = 10; i < 30; ++i)
            link.add(i);
        for (int j = 5; j < 20; ++j)
            linkB.add(j);
        LinkedList newLink = link.intersection(linkB);
        assertEquals(35, newLink.size());
    }
}
