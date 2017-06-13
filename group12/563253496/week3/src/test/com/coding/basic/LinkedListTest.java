package test.com.coding.basic;

import com.coding.basic.LinkedList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import sun.awt.image.ImageWatched;

/**
 * LinkedList Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ÈýÔÂ 9, 2017</pre>
 */
public class LinkedListTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: add(Object o)
     */
    @Test
    public void testAddO() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: add(int index, Object o)
     */
    @Test
    public void testAddForIndexO() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: get(int index)
     */
    @Test
    public void testGet() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: remove(int index)
     */
    @Test
    public void testRemoveIndex() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getNode(int index)
     */
    @Test
    public void testGetNode() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: size()
     */
    @Test
    public void testSize() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: addFirst(Object o)
     */
    @Test
    public void testAddFirst() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: addLast(Object o)
     */
    @Test
    public void testAddLast() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: removeFirst()
     */
    @Test
    public void testRemoveFirst() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: removeLast()
     */
    @Test
    public void testRemoveLast() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: iterator()
     */
    @Test
    public void testIterator() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: reverse()
     */
    @Test
    public void testReverse() throws Exception {
//TODO: Test goes here...
        LinkedList ll = new LinkedList();
        ll.add(3);
        ll.add(7);
        ll.add(10);
        ll.reverse();
        Assert.assertEquals("10->7->3", ll.toString());

    }

    /**
     * Method: removeFirstHalf()
     */
    @Test
    public void testRemoveFirstHalf() throws Exception {
//TODO: Test goes here...
        LinkedList ll = new LinkedList();
        ll.add(2);
        ll.add(5);
        ll.add(7);
        ll.add(8);
        ll.add(10);
        ll.removeFirstHalf();
        Assert.assertEquals("7->8->10", ll.toString());
    }

    /**
     * Method: remove(int i, int length)
     */
    @Test
    public void testRemoveForILength() throws Exception {
//TODO: Test goes here...
        LinkedList ll = new LinkedList();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);
        ll.add(6);
        ll.add(7);
        ll.remove(2, 2);
        Assert.assertEquals("1->2->5->6->7", ll.toString());

    }

    /**
     * Method: getElements(LinkedList list)
     */
    @Test
    public void testGetElements() throws Exception {
//TODO: Test goes here...
        LinkedList ll = new LinkedList();
        ll.add(11);
        ll.add(101);
        ll.add(201);
        ll.add(301);
        ll.add(401);
        ll.add(501);
        ll.add(601);
        ll.add(701);
        LinkedList listB = new LinkedList();
        listB.add(1);
        listB.add(3);
        listB.add(4);
        listB.add(6);
        int[] result = ll.getElements(listB);
        int[] exresult = {101, 301, 401, 601};
        for (int i = 0; i < result.length; i++) {
            Assert.assertEquals(exresult[i], result[i]);
        }
        //Assert.assertEquals("[101,301,401,601]",result.toString());
    }

    /**
     * Method: subtract(LinkedList list)
     */
    @Test
    public void testSubtract() throws Exception {
//TODO: Test goes here...
        LinkedList ll = new LinkedList();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);
        ll.add(6);
        LinkedList listB = new LinkedList();
        listB.add(1);
        listB.add(3);
        listB.add(5);
        ll.subtract(listB);
        Assert.assertEquals("2->4->6", ll.toString());

    }

    /**
     * Method: removeDuplicateValues()
     */
    @Test
    public void testRemoveDuplicateValues() throws Exception {
//TODO: Test goes here...
        LinkedList ll = new LinkedList();
        ll.add(1);
        ll.add(2);
        ll.add(2);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(4);
        ll.add(4);
        ll.add(4);
        ll.removeDuplicateValues();
        Assert.assertEquals("1->2->3->4",ll.toString());

    }

    /**
     * Method: removeRange(int min, int max)
     */
    @Test
    public void testRemoveRange() throws Exception {
//TODO: Test goes here...
        LinkedList ll = new LinkedList();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);
        ll.add(6);
        ll.add(7);
        ll.add(8);
        ll.removeRange(3,8);
        Assert.assertEquals("1->2",ll.toString());

    }

    /**
     * Method: intersection(LinkedList list)
     */
    @Test
    public void testIntersection() throws Exception {
//TODO: Test goes here...
        LinkedList ll = new LinkedList();
        LinkedList l = new LinkedList();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);
        ll.add(6);
        l.add(2);
        l.add(4);
        l.add(6);
        l.add(7);
        l.add(8);
        l.add(9);
        LinkedList re = ll.intersection(l);
        Assert.assertEquals("2->4->6",re.toString());
    }

    /**
     * Method: main(String[] args)
     */
    @Test
    public void testMain() throws Exception {
//TODO: Test goes here... 
    }


} 
