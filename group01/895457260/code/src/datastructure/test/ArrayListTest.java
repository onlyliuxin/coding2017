package test.datastructure.basic;

import datastructure.basic.ArrayList;
import datastructure.basic.Iterator;
import datastructure.basic.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * ArrayList Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>二月 24, 2017</pre>
 */
public class ArrayListTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    protected final List getList() {
        List list = createList();
        init(list);
        return list;
    }

    List createList() {
        return new ArrayList(5);
    }

    private void init(List list) {
        for (int i = 1; i <= 5; ++i) {
            list.add(i);
        }
    }

    protected final Object[] toArray(List list) {
        Object[] array = new Object[list.size()];
        Iterator iterator = list.iterator();
        int pos = 0;
        while (iterator.hasNext()) {
            array[pos++] = iterator.next();
        }
        return array;
    }

    /**
     * Method: add(Object o)
     */
    @Test
    public void testAddO() throws Exception {
//TODO: Test goes here...
        List list = getList();
        for (int i = 6; i <= 10; ++i) {
            list.add(i);
        }
        Assert.assertArrayEquals(toArray(list), new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        Assert.assertEquals(list.size(), 10);
    }

    /**
     * Method: add(int index, Object o)
     */
    @Test
    public void testAddForIndexO() throws Exception {
//TODO: Test goes here...
        List list = getList();
        int nowSize = list.size();
        int[] indexes = {nowSize + 1, -1, nowSize, nowSize, 0, 1};
        Object[] values = {0, 0, 300, 400, 100, 200};
        boolean[] exceptions = new boolean[indexes.length];
        for (int i = 0; i < indexes.length; ++i) {
            try {
                list.add(indexes[i], values[i]);
            } catch (IndexOutOfBoundsException e) {
                exceptions[i] = true;
            }
        }
        Assert.assertArrayEquals(toArray(list), new Object[]{100, 200, 1, 2, 3, 4, 5, 400, 300});
        Assert.assertArrayEquals(exceptions, new boolean[]{true, true, false, false, false, false});
        Assert.assertEquals(list.size(), nowSize + 4);
    }

    /**
     * Method: get(int index)
     */
    @Test
    public void testGet() throws Exception {
//TODO: Test goes here...
        List list = getList();
        int nowSize = list.size();
        int[] indexes = {-1, nowSize, 0, 1, nowSize - 1, nowSize - 2};
        Object[] values = new Object[indexes.length];
        boolean[] exceptions = new boolean[indexes.length];
        for (int i = 0; i < indexes.length; ++i) {
            try {
                values[i] = list.get(indexes[i]);
            } catch (IndexOutOfBoundsException e) {
                exceptions[i] = true;
            }
        }
        Assert.assertArrayEquals(values, new Object[]{null, null, 1, 2, 5, 4});
        Assert.assertArrayEquals(exceptions, new boolean[]{true, true, false, false, false, false});
        Assert.assertEquals(list.size(), nowSize);
    }

    /**
     * Method: remove(int index)
     */
    @Test
    public void testRemove() throws Exception {
//TODO: Test goes here...
        List list = getList();
        int nowSize = list.size();
        int[] indexes = {-1, nowSize, nowSize - 2, nowSize - 2, 1, 0};
        Object[] values = new Object[indexes.length];
        boolean[] exceptions = new boolean[indexes.length];
        for (int i = 0; i < indexes.length; ++i) {
            try {
                values[i] = list.remove(indexes[i]);
            } catch (IndexOutOfBoundsException e) {
                exceptions[i] = true;
            }
        }
        Assert.assertArrayEquals(values, new Object[]{null, null, 4, 5, 2, 1});
        Assert.assertArrayEquals(exceptions, new boolean[]{true, true, false, false, false, false});
        Assert.assertEquals(list.size(), nowSize - 4);
    }

    /**
     * Method: iterator()
     */
    @Test
    public void testIterator() throws Exception {
//TODO: Test goes here...
        List list = getList();
        Iterator iterator = list.iterator();
        Object[] values = new Object[list.size()];
        int pos = 0;
        while (iterator.hasNext()) {
            values[pos++] = iterator.next();
        }
        Assert.assertArrayEquals(values, new Object[]{1, 2, 3, 4, 5});
    }
} 
