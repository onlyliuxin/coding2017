package datastructure.linkedlist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


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

    // =========================第三周作业=========================

    @Test
    public void testReverse() {
        linkedList.reverse();
        for (int i = 0; i < linkedList.size(); i++) {
            Assert.assertEquals(499 - i, linkedList.get(i));
        }
        Assert.assertEquals(500, linkedList.size());
    }

    @Test
    public void testRemoveFirstHalfOfEven() {
        linkedList.removeFirstHalf();
        for (int i = 250; i < 500; i++) {
            Assert.assertEquals(i, linkedList.get(i - 250));
        }
        Assert.assertEquals(250, linkedList.size());
    }

    @Test
    public void testRemoveFirstHalfOfOdd() {
        linkedList.add(500);
        linkedList.removeFirstHalf();
        for (int i = 250; i < 501; i++) {
            Assert.assertEquals(i, linkedList.get(i - 250));
        }
        Assert.assertEquals(251, linkedList.size());
    }

    @Test
    public void testRemoveIntInt() {
        linkedList.remove(10, 10);
        Assert.assertEquals(0, linkedList.get(0));
        Assert.assertEquals(20, linkedList.get(10));
        Assert.assertEquals(490, linkedList.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalIRemoveIntInt() {
        linkedList.remove(-10, 10);
    }

    @Test
    public void testIllegalLengthRemoveIntInt() {
        linkedList.remove(0, 10);
        Assert.assertEquals(490, linkedList.size());
        Assert.assertEquals(10, linkedList.get(0));

        linkedList.remove(300, 500);
        Assert.assertEquals(300, linkedList.size());
        for (int i = 0; i < 300; i++) {
            Assert.assertEquals(i + 10, linkedList.get(i));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetElements() {
        LinkedList list = new LinkedList();
        list.add(5);
        list.add(10);
        list.add(202);
        list.add(305);
        Assert.assertArrayEquals(new int[] {5, 10, 202, 305}, linkedList.getElements(list));

        Assert.assertEquals(500, linkedList.size());

        list.add(500);
        linkedList.getElements(list);
    }

    @Test
    public void testSubtract() {
        LinkedList list1 = new LinkedList();
        list1.add(1);
        list1.add(5);
        list1.add(8);
        list1.add(10);
        LinkedList list2 = new LinkedList();
        list2.add(2);
        list2.add(5);
        list2.add(7);
        list2.add(11);
        list1.subtract(list2);

        Assert.assertEquals(1, list1.get(0));
        Assert.assertEquals(8, list1.get(1));
        Assert.assertEquals(10, list1.get(2));
        Assert.assertEquals(3, list1.size());
    }

    @Test
    public void testRemoveDuplicateValues() {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(6);
        list.add(7);
        list.add(7);
        list.add(7);

        list.removeDuplicateValues();
        for (int i = 0; i < 7; i++) {
            Assert.assertEquals(i + 1, list.get(i));
        }
        Assert.assertEquals(7, list.size());
    }

    @Test
    public void testRemoveRange() {
        linkedList.removeRange(100, 300);
        Assert.assertEquals(301, linkedList.size());
        for (int i = 0; i < 100; i++) {
            Assert.assertEquals(i, linkedList.get(i));
        }
        for (int i = 300; i < 500; i++) {
            Assert.assertEquals(i, linkedList.get(i - 199));
        }
    }

    @Test
    public void testIntersection() {
        LinkedList list1 = new LinkedList();
        list1.add(1);
        list1.add(2);
        list1.add(5);
        list1.add(7);
        list1.add(9);

        LinkedList list2 = new LinkedList();
        list2.add(0);
        list2.add(2);
        list2.add(9);
        list2.add(10);
        list2.add(19);

        LinkedList intersection = list1.intersection(list2);
        Assert.assertNotNull(intersection);
        Assert.assertEquals(2, intersection.size());
        Assert.assertEquals(2, (int) intersection.get(0));
        Assert.assertEquals(9, (int) intersection.get(1));


    }
}
