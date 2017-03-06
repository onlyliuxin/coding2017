package org.xukai.common;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xukai
 * @desc
 * @date 2017-02-20-下午 2:02
 */
public class ArrayListTest {

    @Test
    public void testAdd() throws Exception {
        ArrayList list = new ArrayList();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        Assert.assertTrue(list.size() == 5);
        list.add(0,"3");
        Assert.assertTrue(list.get(0).equals("3"));
        Assert.assertTrue(list.size() == 6);
        list.remove(5);
        Assert.assertTrue(list.size() == 5);
        list.display();
    }

    @Test
    public void testAdd1() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testRemove() throws Exception {

    }

    @Test
    public void testSize() throws Exception {

    }

    @Test
    public void testIterator() throws Exception {
        ArrayList list = new ArrayList();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testDisplay() throws Exception {

    }
}