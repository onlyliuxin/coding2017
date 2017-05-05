package com.coding.basic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by damocles on 2017/3/6.
 */
public class ArrayListTest {
    private ArrayList<String> bookList;

    @Before
    public void setUp() throws Exception {
        bookList = new ArrayList<>();

        bookList.add("java");
        bookList.add("javascript");
        bookList.add("c++");
    }

    @After
    public void tearDown() throws Exception {
        bookList = null;
    }

    @Test
    public void add() throws Exception {
        Assert.assertTrue(bookList.add("javaScript"));
    }

    @Test
    public void add1() throws Exception {
        Assert.assertEquals("java", bookList.get(0));
        Assert.assertEquals("c++", bookList.get(2));
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals("java", bookList.get(0));
    }

    @Test
    public void remove() throws Exception {
        Assert.assertEquals("javascript", bookList.remove(1));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, bookList.size());
    }

    @Test
    public void isEmpty() throws Exception {
        Assert.assertFalse(bookList.isEmpty());
    }

    @Test
    public void iterator() throws Exception {

        Iterator<String> it = bookList.iterator();
        Assert.assertTrue(it.hasNext());
        int count = 0;
        while (it.hasNext()) {
            Assert.assertEquals(bookList.get(count++), it.next());
        }
    }

}