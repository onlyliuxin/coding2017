package main.coding_170309;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by peter on 2017/3/11.
 */
public class LinkedListTest extends TestCase {
    LinkedList linkedList;
    @Before
    public void setUp() throws Exception {
         linkedList = new LinkedList();
    }

    @After
    public void tearDown() throws Exception {
        linkedList = null;
    }

    @org.junit.Test
    public void testAdd() throws Exception {
        linkedList.add(1);
        linkedList.add(2);
        System.out.println(linkedList);
    }

    @Test
    public void testAdd1() throws Exception {
        linkedList.add(12,0);
        linkedList.add(15,1);
        linkedList.add(20,1);
        System.out.println(linkedList);
    }

    @Test
    public void testGet() throws Exception {
        linkedList.add(12);
        Assert.assertEquals(12,(int)linkedList.get(0));
    }

    @Test
    public void testRemove() throws Exception {
        linkedList.add(15);
        linkedList.add(20);
        linkedList.remove(0);
        System.out.println(linkedList.getSize());
    }

    @Test
    public void testGetSize() throws Exception {
        linkedList.add(15);
        linkedList.add(25);
        linkedList.add(30);
        linkedList.add(45);
        System.out.println(linkedList.getSize());
    }

    @Test
    public void testReverse() throws Exception {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        System.out.println(linkedList);
        linkedList.reverse();
        System.out.println(linkedList);
    }

    @Test
    public void testRemoveFirstHalf() throws Exception {
        linkedList.add(10);
        linkedList.add(11);
        linkedList.add(12);
        linkedList.add(13);
        linkedList.add(14);
        linkedList.add(15);
        System.out.println(linkedList);
        linkedList.removeFirstHalf();
        System.out.println(linkedList);
    }

    @Test
    public void testRemove1() throws Exception {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.remove(1,2);
        System.out.println(linkedList);
    }

    @Test
    public void testGetElements() throws Exception {
        linkedList.add(12);
        linkedList.add(13);
        linkedList.add(14);
        linkedList.add(15);
        linkedList.add(16);
        LinkedList list = new LinkedList();
        list.add(2);
        list.add(3);
        list.add(4);
        int[] data = linkedList.getElements(list);
        System.out.println(Arrays.toString(data));
    }

    @Test
    public void testSubtract() throws Exception {
        linkedList.add(12);
        linkedList.add(13);
        linkedList.add(14);
        linkedList.add(15);
        linkedList.add(16);
        LinkedList list = new LinkedList();
        list.add(12);
        list.add(14);
        list.add(15);
        linkedList.subtract(list);
        System.out.println(linkedList);
    }

    @Test
    public void testRemoveDuplicateValues() throws Exception {
        linkedList.add(10);
        linkedList.add(11);
        linkedList.add(11);
        linkedList.add(12);
        linkedList.add(13);
        linkedList.add(14);
        linkedList.add(14);
    }

    @Test
    public void testRemoveRange() throws Exception {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.removeRange(2,4);
        System.out.println(linkedList);
    }

    @Test
    public void testIntersection() throws Exception {
        linkedList.add(12);
        linkedList.add(13);
        linkedList.add(14);
        linkedList.add(15);
        linkedList.add(16);
        LinkedList list = new LinkedList();
        list.add(12);
        list.add(14);
        list.add(15);
        list.add(17);
        list.add(18);
        System.out.println(linkedList.intersection(list));

    }

}