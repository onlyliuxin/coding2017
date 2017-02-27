package test.datastructure.basic;

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
}
