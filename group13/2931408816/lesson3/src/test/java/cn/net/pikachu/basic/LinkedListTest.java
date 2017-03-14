package cn.net.pikachu.basic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * LinkedList Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 12, 2017</pre>
 */
public class LinkedListTest {
    private LinkedList list = null;
    @Before
    public void before() throws Exception {
        list = new LinkedList();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: add(Object o)
     */
    @Test
    public void testAddO() throws Exception {
    }

    /**
     * Method: add(int index, Object o)
     */
    @Test
    public void testAddForIndexO() throws Exception {
    }

    /**
     * Method: get(int index)
     */
    @Test
    public void testGet() throws Exception {
    }

    /**
     * Method: remove(int index)
     */
    @Test
    public void testRemoveIndex() throws Exception {
    }

    /**
     * Method: size()
     */
    @Test
    public void testSize() throws Exception {
    }

    /**
     * Method: addFirst(Object o)
     */
    @Test
    public void testAddFirst() throws Exception {
    }

    /**
     * Method: addLast(Object o)
     */
    @Test
    public void testAddLast() throws Exception {
    }

    /**
     * Method: removeFirst()
     */
    @Test
    public void testRemoveFirst() throws Exception {
    }

    /**
     * Method: removeLast()
     */
    @Test
    public void testRemoveLast() throws Exception {
    }

    /**
     * Method: iterator()
     */
    @Test
    public void testIterator() throws Exception {
    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() throws Exception {
    }

    /**
     * Method: reverse()
     */
    @Test
    public void testReverse() throws Exception {
        list.add(3);
        list.add(7);
        list.add(10);
        list.reverse();
        Assert.assertEquals("[10,7,3]",list.toString());
    }

    /**
     * Method: removeFirstHalf()
     */
    @Test
    public void testRemoveFirstHalf() throws Exception {
        list.add(2);
        list.add(5);
        list.add(7);
        list.add(8);
        list.removeFirstHalf();
        Assert.assertEquals("[7,8]",list.toString());

        list = new LinkedList();
        list.add(2);
        list.add(5);
        list.add(7);
        list.add(8);
        list.add(10);
        list.removeFirstHalf();
        Assert.assertEquals("[7,8,10]",list.toString());
    }

    /**
     * Method: remove(int i, int length)
     */
    @Test
    public void testRemoveForILength() throws Exception {
        list.add(2);
        list.add(5);
        list.add(7);
        list.add(8);
        list.add(10);
        list.remove(2,3);
        Assert.assertEquals("[2,5]",list.toString());
    }

    /**
     * Method: getElements(LinkedList list)
     */
    @Test
    public void testGetElements() throws Exception {
        list.add(11);
        list.add(101);
        list.add(201);
        list.add(301);
        list.add(401);
        list.add(501);
        list.add(601);
        list.add(701);

        LinkedList l = new LinkedList();
        l.add(1);
        l.add(3);
        l.add(4);
        l.add(6);

        int[] a = new int[]{101,301,401,601};
        Assert.assertArrayEquals(a,list.getElements(l));
    }

    /**
     * Method: subtract(LinkedList list)
     */
    @Test
    public void testSubtract() throws Exception {
        list.add(2);
        list.add(5);
        list.add(7);
        list.add(8);
        list.add(10);

        LinkedList l = new LinkedList();
        l.add(2);
        l.add(5);
        l.add(7);
        l.add(8);

        list.subtract(l);
        Assert.assertEquals("[10]",list.toString());
    }

    /**
     * Method: removeDuplicateValues()
     */
    @Test
    public void testRemoveDuplicateValues() throws Exception {
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(7);
        list.add(7);
        list.add(7);
        list.add(7);
        list.add(8);
        list.add(8);
        list.add(8);
        list.add(8);
        list.add(10);
        list.add(10);
        list.add(10);
        list.add(10);
        list.removeDuplicateValues();
        Assert.assertEquals("[2,5,7,8,10]",list.toString());
    }

    /**
     * Method: removeRange(int min, int max)
     */
    @Test
    public void testRemoveRange() throws Exception {
        list.add(2);
        list.add(5);
        list.add(7);
        list.add(8);
        list.add(10);
        list.removeRange(5,9);
        Assert.assertEquals("[2,5,10]",list.toString());
    }

    /**
     * Method: intersection(LinkedList list)
     */
    @Test
    public void testIntersection() throws Exception {

        list.add(2);
        list.add(5);
        list.add(7);
        list.add(10);


        LinkedList l = new LinkedList();
        l.add(2);
        l.add(7);
        l.add(8);
        l.add(10);

        Assert.assertEquals("[2,7,10]",list.intersection(l).toString()); ;
    }

    /**
     * Method: hasNext()
     */
    @Test
    public void testHasNext() throws Exception {
    }

    /**
     * Method: next()
     */
    @Test
    public void testNext() throws Exception {
    }


} 
