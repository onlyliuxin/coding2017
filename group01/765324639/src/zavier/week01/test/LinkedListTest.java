package zavier.week01.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import zavier.week01.basic.LinkedList;


public class LinkedListTest {

    private LinkedList linkedList = new LinkedList();

    @Before
    public void setUp() {
        for (int i = 0; i < 500; i++) {
            linkedList.add(i);
        }
    }

    @Test
    public void testAddObject() {
        for (int i = 0; i < 100; i++) {
            linkedList.add(i);
        }
        Assert.assertEquals(600, linkedList.size());
    }

    @Test
    public void testAddIntObject() {
        linkedList.add(100, -100);
        Assert.assertEquals(-100, linkedList.get(100));
        Assert.assertEquals(100, linkedList.get(101));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIllegalIntObject() {
        linkedList.add(1000, 10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddNegativeIntObject() {
        linkedList.add(-10, 10);
    }

    @Test
    public void testGet() {
        for (int i = 0; i < 500; i++) {
            Assert.assertEquals(i, ((Integer) linkedList.get(i)).intValue());
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIllegalGet() {
        linkedList.get(500);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testNegativeGet() {
        linkedList.get(-10);
    }

    @Test
    public void testRemove() {
        Assert.assertEquals(100, linkedList.remove(100));
        Assert.assertEquals(101, linkedList.get(100));
        Assert.assertEquals(499, linkedList.size());
    }

    @Test
    public void testSize() {
        Assert.assertEquals(500, linkedList.size());
        linkedList.add(10);
        Assert.assertEquals(501, linkedList.size());
    }

    @Test
    public void testAddFirst() {
        linkedList.addFirst(-10);
        Assert.assertEquals(-10, linkedList.get(0));
        linkedList.addFirst(-100);
        Assert.assertEquals(-100, linkedList.get(0));
        Assert.assertEquals(-10, linkedList.get(1));
    }

    @Test
    public void testAddLast() {
        linkedList.addLast(-9);
        Assert.assertEquals(-9, linkedList.get(linkedList.size() - 1));
        linkedList.addLast(-8);
        Assert.assertEquals(-8, linkedList.get(linkedList.size() - 1));
        Assert.assertEquals(-9, linkedList.get(linkedList.size() - 2));
    }

    @Test
    public void testRemoveFirst() {
        Assert.assertEquals(0, linkedList.removeFirst());
        Assert.assertEquals(1, linkedList.removeFirst());
        Assert.assertEquals(498, linkedList.size());
    }

    @Test
    public void testRemoveLast() {
        Assert.assertEquals(499, linkedList.removeLast());
        Assert.assertEquals(498, linkedList.removeLast());
        Assert.assertEquals(498, linkedList.size());
    }

}
