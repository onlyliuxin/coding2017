package test.com.java.xiaoqin.linkedlist;

import com.java.xiaoqin.linkedlist.QIterator;
import com.java.xiaoqin.linkedlist.QLinkedList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Arrays;

/**
 * QLinkedList Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 7, 2017</pre>
 */
public class QLinkedQListTest {

    QLinkedList linkedList = null;

    @Before
    public void before() throws Exception {
        linkedList = new QLinkedList();
    }

    @After
    public void after() throws Exception {
        linkedList = null;
    }

    /**
     * Method: add(Object o)
     */
    @Test
    public void testAddO() throws Exception {
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        System.out.println(linkedList.toString());
    }

    /**
     * Method: add(int index, Object o)
     */
    @Test
    public void testAddForIndexO() throws Exception {
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add(3, "10");
        System.out.println(linkedList.toString());
    }

    /**
     * Method: get(int index)
     */
    @Test
    public void testGet() throws Exception {
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        System.out.println(linkedList.get(1));
    }

    /**
     * Method: remove(int index)
     */
    @Test
    public void testRemoveIndex() throws Exception {
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        System.out.println(linkedList.remove(1));
        System.out.println(linkedList.toString());
    }

    /**
     * Method: size()
     */
    @Test
    public void testSize() throws Exception {
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        System.out.println(linkedList.size());
    }

    /**
     * Method: addFirst(Object o)
     */
    @Test
    public void testAddFirst() throws Exception {
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.addFirst("0");
        System.out.println(linkedList);
    }

    /**
     * Method: addLast(Object o)
     */
    @Test
    public void testAddLast() throws Exception {
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.addLast("4");
        System.out.println(linkedList);
    }

    /**
     * Method: removeFirst()
     */
    @Test
    public void testRemoveFirst() throws Exception {
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.removeFirst();
        System.out.println(linkedList);
        Assert.assertEquals(2, linkedList.size());
    }

    /**
     * Method: removeLast()
     */
    @Test
    public void testRemoveLast() throws Exception {
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.removeLast();
        System.out.println(linkedList);
        Assert.assertEquals(2, linkedList.size());
    }

    /**
     * Method: iterator()
     */
    @Test
    public void testIterator() throws Exception {
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        QIterator iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        Assert.assertEquals(3, linkedList.size());
    }

    /**
     * Method: reverse()
     */
    @Test
    public void testReverse() throws Exception {
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.reverse();
        System.out.println(linkedList);
    }

    /**
     * Method: removeFirstHalf()
     */
    @Test
    public void testRemoveFirstHalf() throws Exception {
        linkedList.add(2);
        linkedList.add(5);
        linkedList.add(7);
        linkedList.add(8);
        linkedList.removeFirstHalf();
        System.out.println(linkedList);
        Assert.assertEquals(2, linkedList.size());

        before();
        linkedList.add(2);
        linkedList.add(5);
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(10);
        linkedList.removeFirstHalf();
        System.out.println(linkedList);
        Assert.assertEquals(3, linkedList.size());
    }

    /**
     * Method: remove(int i, int length)
     */
    @Test
    public void testRemoveForILength() throws Exception {
        linkedList.add(2);
        linkedList.add(5);
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(10);
        linkedList.remove(0, 2);
        System.out.println(linkedList);
        Assert.assertEquals(2, linkedList.size());
    }

    /**
     * Method: getElements(QLinkedList list)
     */
    @Test
    public void testGetElements() throws Exception {
        linkedList.add(2);
        linkedList.add(5);
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(10);
        QLinkedList qLinkedList = new QLinkedList();
        qLinkedList.add(2);
        qLinkedList.add(3);
        qLinkedList.add(4);
        int[] elements = linkedList.getElements(qLinkedList);
        System.out.println(Arrays.toString(elements));
    }

    /**
     * Method: subtract(QLinkedList list)
     */
    @Test
    public void testSubtract() throws Exception {
        linkedList.add(2);
        linkedList.add(5);
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(10);
        QLinkedList qLinkedList = new QLinkedList();
        qLinkedList.add(5);
        qLinkedList.add(8);
        linkedList.subtract(qLinkedList);
        System.out.println(linkedList);
    }

    /**
     * Method: removeDuplicateValues()
     */
    @Test
    public void testRemoveDuplicateValues() throws Exception {
        linkedList.add(2);
        linkedList.add(5);
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(10);
        linkedList.removeDuplicateValues();
        System.out.println(linkedList);
    }

    /**
     * Method: removeRange(int min, int max)
     */
    @Test
    public void testRemoveRange() throws Exception {
        linkedList.add(2);
        linkedList.add(5);
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(10);
        linkedList.removeRange(5,9);
        System.out.println(linkedList);
    }

    /**
     * Method: intersection(QLinkedList list)
     */
    @Test
    public void testIntersection() throws Exception {
        linkedList.add(2);
        linkedList.add(5);
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(10);
        QLinkedList qLinkedList = new QLinkedList();
        qLinkedList.add(4);
        qLinkedList.add(5);
        qLinkedList.add(7);
        System.out.println(linkedList.intersection(qLinkedList));
    }

}
