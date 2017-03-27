package com.coding.basic;

import org.intellij.lang.annotations.Flow;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by damocles on 2017/3/6.
 */
public class LinkedListTest {
    private LinkedList<String> bookList;

    @Before
    public void setUp() throws Exception {
        bookList = new LinkedList<>();

        bookList.add("javascript");
        bookList.add("java");
        bookList.add("c++");
        bookList.add("c");

    }

    @After
    public void tearDown() throws Exception {
        bookList = null;
    }

    @Test
    public void add() throws Exception {
        Assert.assertTrue(bookList.add("python"));
    }

    @Test
    public void add1() throws Exception {
        bookList.add("python");
        Assert.assertEquals("python", bookList.removeLast());
    }

    @Test
    public void addAll() throws Exception {

    }

    @Test
    public void addFirst() throws Exception {
        bookList.addFirst("python");
        Assert.assertEquals("python", bookList.removeFirst());
    }

    @Test
    public void addLast() throws Exception {
        bookList.addLast("python");
        Assert.assertEquals("python", bookList.removeLast());
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals("javascript", bookList.get(0));
    }

    @Test
    public void remove() throws Exception {
        Assert.assertEquals("javascript", bookList.remove(0));
    }

    @Test
    public void removeFirst() throws Exception {
        Assert.assertEquals("javascript", bookList.removeFirst());
    }

    @Test
    public void removeLast() throws Exception {
        Assert.assertEquals("c", bookList.removeLast());
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(4, bookList.size());
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