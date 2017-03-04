package com.coding.basic;

import org.junit.*;
import org.junit.rules.ExpectedException;

/**
 * Created by mark on 17/2/24.
 */
public class LinkedListTest {

    private LinkedList linkedList;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        linkedList = new LinkedList();
    }

    @After
    public void tearDown() throws Exception {
        linkedList = null;
    }

    @Test
    public void add() throws Exception {
        linkedList.add("first");
        Assert.assertEquals(1, linkedList.size());
        Assert.assertEquals("first", linkedList.get(0));

        linkedList.add("second");
        linkedList.add("third");
        Assert.assertEquals("third", linkedList.get(2));
    }

    @Test
    public void add1() throws Exception {
        for (int i=0; i<10; i++) {
            linkedList.add(i);
        }
        linkedList.add(5, "Five");
        Assert.assertEquals("Five", linkedList.get(5));
        Assert.assertEquals(11, linkedList.size());

        linkedList.add(0, "Zero");
        Assert.assertEquals("Zero", linkedList.get(0));

        linkedList.add(12, "Last");
        Assert.assertEquals("Last", linkedList.get(12));
    }

    @Test
    public void get() throws Exception {

        linkedList.add("hello");
        Assert.assertEquals("hello", linkedList.get(0));

        linkedList.add("two");
        Assert.assertEquals("two", linkedList.get(1));

        linkedList = new LinkedList();
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        linkedList.get(0);
    }

    @Test
    public void remove() throws Exception {
        Object data = null;

        for (int i=0; i<10; i++) {
            linkedList.add("" + i);
        }

        data = linkedList.remove(0);
        Assert.assertEquals("0", data);

        data = linkedList.remove(8);
        Assert.assertEquals("9", data);

        data = linkedList.remove(4);
        Assert.assertEquals("5", data);
    }

    @Test
    public void size() throws Exception {
        linkedList.add(0);
        Assert.assertEquals(1, linkedList.size());
    }

    @Test
    public void addFirst() throws Exception {

    }

    @Test
    public void addLast() throws Exception {

    }

    @Test
    public void removeFirst() throws Exception {

    }

    @Test
    public void removeLast() throws Exception {

    }

    @Test
    public void iterator() throws Exception {

    }

}