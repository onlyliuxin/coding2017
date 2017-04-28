package datastructure;

import datastructure.exception.EmptyListException;
import datastructure.basic.LinkedList;
import datastructure.basic.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * LinkedList Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>二月 24, 2017</pre>
 */
public class LinkedListTest extends ArrayListTest {

    @Override
    List createList() {
        return new LinkedList();
    }

    /**
     * Method: addFirst(Object o)
     */
    @Test
    public void testAddFirst() throws Exception {
//TODO: Test goes here...
        LinkedList list = (LinkedList) getList();
        list.addFirst(100);
        Assert.assertArrayEquals(toArray(list), new Object[]{100, 1, 2, 3, 4, 5});
    }

    /**
     * Method: addLast(Object o)
     */
    @Test
    public void testAddLast() throws Exception {
//TODO: Test goes here...
        LinkedList list = (LinkedList) getList();
        list.addLast(100);
        Assert.assertArrayEquals(toArray(list), new Object[]{1, 2, 3, 4, 5, 100});
    }

    /**
     * Method: removeFirst()
     */
    @Test
    public void testRemoveFirst() throws Exception {
//TODO: Test goes here...
        LinkedList list = (LinkedList) getList();
        int count = list.size() + 2;
        Object[] values = new Object[count];
        boolean[] exceptions = new boolean[count];
        for (int i = 0; i < count; ++i) {
            try {
                values[i] = list.removeFirst();
            } catch (EmptyListException e) {
                exceptions[i] = true;
            }
        }
        Assert.assertArrayEquals(values, new Object[]{1, 2, 3, 4, 5, null, null});
        Assert.assertArrayEquals(exceptions, new boolean[]{false, false, false, false, false, true, true});
        Assert.assertArrayEquals(toArray(list), new Object[0]);
    }

    /**
     * Method: removeLast()
     */
    @Test
    public void testRemoveLast() throws Exception {
//TODO: Test goes here...
        LinkedList list = (LinkedList) getList();
        int count = list.size() + 2;
        Object[] values = new Object[count];
        boolean[] exceptions = new boolean[count];
        for (int i = 0; i < count; ++i) {
            try {
                values[i] = list.removeLast();
            } catch (EmptyListException e) {
                exceptions[i] = true;
            }
        }
        Assert.assertArrayEquals(values, new Object[]{5, 4, 3, 2, 1, null, null});
        Assert.assertArrayEquals(exceptions, new boolean[]{false, false, false, false, false, true, true});
        Assert.assertArrayEquals(toArray(list), new Object[0]);
    }

    /**
     *
     * Method: reverse()
     *
     */
    @Test
    public void testReverse() throws Exception {
//TODO: Test goes here...
        LinkedList list = (LinkedList) getList();
        list.reverse();
        Assert.assertArrayEquals(toArray(list), new Object[] {5, 4, 3, 2, 1});
    }

    /**
     *
     * Method: removeFirstHalf()
     *
     */
    @Test
    public void testRemoveFirstHalf() throws Exception {
//TODO: Test goes here...
        LinkedList list = (LinkedList) getList();
        list.removeFirstHalf();
        Assert.assertArrayEquals(toArray(list), new Object[] {3, 4, 5});
    }

    /**
     *
     * Method: remove(int i, int size)
     *
     */
    @Test
    public void testRemoveForILength() throws Exception {
//TODO: Test goes here...
        LinkedList list = (LinkedList) getList();
        list.remove(1, 3);
        Assert.assertArrayEquals(toArray(list), new Object[] {1, 5});
    }

    /**
     *
     * Method: getElements(LinkedList list)
     *
     */
    @Test
    public void testGetElements() throws Exception {
//TODO: Test goes here...
        LinkedList list = (LinkedList) getList();
        LinkedList indexList = new LinkedList();
        for (int i = 0; i < 3; ++i) {
            indexList.add(2 * i);
        }
        Object[] elements = list.getElements(indexList);
        Assert.assertArrayEquals(elements, new Object[] {1, 3, 5});
    }

    /**
     *
     * Method: subtract(LinkedList list)
     *
     */
    @Test
    public void testSubtract() throws Exception {
//TODO: Test goes here...
        LinkedList list = (LinkedList) getList();
        LinkedList removeList = new LinkedList();
        for (int i = 0; i < 3; ++i) {
            removeList.add(2 * i);
        }
        list.subtract(removeList);
        Assert.assertArrayEquals(toArray(list), new Object[] {1, 3, 5});
    }

    /**
     *
     * Method: removeDuplicateValues()
     *
     */
    @Test
    public void testRemoveDuplicateValues() throws Exception {
//TODO: Test goes here...
        LinkedList list = (LinkedList) getList();
        list.add(5);
        list.add(6);
        list.add(8);
        list.add(8);
        list.add(9);
        list.removeDuplicateValues();
        Assert.assertArrayEquals(toArray(list), new Object[] {1, 2, 3, 4, 5, 6, 8, 9});
    }

    /**
     *
     * Method: removeRange(int min, int max)
     *
     */
    @Test
    public void testRemoveRange() throws Exception {
//TODO: Test goes here...
        LinkedList list = (LinkedList) getList();
        list.removeRange(2, 5);
        Assert.assertArrayEquals(toArray(list), new Object[] {1, 2, 5});
    }

    /**
     *
     * Method: intersection(LinkedList list)
     *
     */
    @Test
    public void testIntersection() throws Exception {
//TODO: Test goes here...
        LinkedList list = (LinkedList) getList();
        LinkedList list1 = new LinkedList();
        for (int i = 0; i < 4; ++i) {
            list1.add(2 * i);
        }
        LinkedList result = list.intersection(list1);
        Assert.assertArrayEquals(toArray(result), new Object[] {2, 4});
    }

    @Test
    public void testMultiMethod() throws Exception {
        LinkedList list = (LinkedList) getList();
        LinkedList subtractList = new LinkedList();
        for (int i = 0; i < 3; ++i) {
            subtractList.add(2 * i);
        }
        LinkedList intersectionList = new LinkedList();
        for (int i = 0; i < 4; ++i) {
            intersectionList.add(2 * i);
        }

        list.reverse();
        list.subtract(subtractList);
        Assert.assertArrayEquals(toArray(list), new Object[] {5, 4, 3, 2, 1});
        LinkedList intersection = list.intersection(intersectionList);
        Assert.assertArrayEquals(toArray(intersection), new Object[] {});
        list.reverse();
        list.add(6);
        list.add(6);
        list.add(6);
        list.add(7);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(9);
        list.add(9);
        list.add(10);
        list.add(11);
        list.removeDuplicateValues();
        Assert.assertArrayEquals(toArray(list), new Object[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        list.remove(0, 0);
        list.remove(0, 2);
        Assert.assertArrayEquals(toArray(list), new Object[] {3, 4, 5, 6, 7, 8, 9, 10, 11});
        list.removeRange(0, 3);
        list.removeRange(11, 13);
        list.removeRange(11, 8);
        list.removeRange(8, 11);
        Assert.assertArrayEquals(toArray(list), new Object[] {3, 4, 5, 6, 7, 8, 11});
        list.removeFirstHalf();
        Assert.assertArrayEquals(toArray(list), new Object[] {6, 7, 8, 11});
    }
}
