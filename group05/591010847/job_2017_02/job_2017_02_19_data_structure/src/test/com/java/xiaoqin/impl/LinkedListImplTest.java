package test.com.java.xiaoqin.impl;

import com.java.xiaoqin.impl.LinkedListImpl;
import com.java.xiaoqin.interfaces.IIterator;
import com.java.xiaoqin.interfaces.IList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * LinkedListImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>二月 26, 2017</pre>
 */
public class LinkedListImplTest {

    private IList<Integer> mList;

    @Before
    public void before() throws Exception {
        mList = new LinkedListImpl<>();
    }

    @After
    public void after() throws Exception {
        mList = null;
    }

    private void addTen() {
        for (int i = 0; i < 10; i++) {
            mList.add(i);
        }
    }

    /**
     * Method: add(T t)
     */
    @Test
    public void testAddT() throws Exception {
        addTen();
        System.out.println(mList.toString());
    }

    /**
     * Method: add(int index, T t)
     */
    @Test
    public void testAddForIndexT() throws Exception {
        addTen();
        mList.add(4, 50);
        mList.add(8, 150);
        System.out.println(mList.toString());
    }

    /**
     * Method: get(int index)
     */
    @Test
    public void testGet() throws Exception {
        addTen();
        Assert.assertEquals(mList.get(5), (Integer) 5);
    }

    /**
     * Method: remove(int index)
     */
    @Test
    public void testRemove() throws Exception {
        addTen();
        Assert.assertEquals(mList.remove(4), (Integer) 4);
        System.out.println(mList);
    }

    /**
     * Method: size()
     */
    @Test
    public void testSize() throws Exception {
        addTen();
        Assert.assertEquals(mList.size(), 10);
    }

    /**
     * Method: isEmpty()
     */
    @Test
    public void testIsEmpty() throws Exception {
        System.out.println(mList.isEmpty());
        addTen();
        System.out.println(mList.isEmpty());
    }

    /**
     * Method: iterator()
     */
    @Test
    public void testIterator() throws Exception {
        addTen();
        IIterator<Integer> iterator = mList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

} 
