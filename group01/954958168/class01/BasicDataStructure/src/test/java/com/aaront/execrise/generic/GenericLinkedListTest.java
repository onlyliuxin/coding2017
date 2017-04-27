package com.aaront.execrise.generic;

import com.aaront.exercise.generic.GenericLinkedList;
import com.aaront.exercise.generic.GenericIterator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tonyhui
 * @since 17/2/22
 */
public class GenericLinkedListTest {
    private GenericLinkedList<String> linkedList = new GenericLinkedList<>();

    @Before
    public void init() {
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
    }

    @Test
    public void testAdd() {
        Assert.assertArrayEquals(linkedList.toArray(), new String[]{"1", "2", "3"});
    }

    @Test
    public void testAddIndex() {
        linkedList.add(1, "10");
        linkedList.add(0, "8");
        Assert.assertArrayEquals(linkedList.toArray(), new String[]{"8", "1", "10", "2", "3"});
    }

    @Test
    public void testAddFirst() {
        linkedList.addFirst("-1");
        Assert.assertArrayEquals(linkedList.toArray(), new String[]{"-1", "1", "2", "3"});
    }

    @Test
    public void testAddLast() {
        linkedList.addLast("99");
        Assert.assertArrayEquals(linkedList.toArray(), new String[]{"1", "2", "3", "99"});
    }

    @Test
    public void testRemove() {
        testAddIndex();
        linkedList.remove(1);
        linkedList.remove(2);
        linkedList.add(3, "3");
        linkedList.add(1, "2");
        Assert.assertArrayEquals(linkedList.toArray(), new String[]{"8", "2", "10", "3", "3"});
    }

    @Test
    public void testRemoveFirst() {
        linkedList.removeFirst();
        linkedList.removeFirst();
        Assert.assertArrayEquals(linkedList.toArray(), new String[]{"3"});
    }

    @Test
    public void testRemoveLast() {
        linkedList.removeLast();
        linkedList.removeLast();
        Assert.assertArrayEquals(linkedList.toArray(), new String[]{"1"});
    }

    @Test
    public void testToArray() {
        Assert.assertArrayEquals(linkedList.toArray(), new String[]{"1", "2", "3"});
    }

    @Test
    public void testToGenericArray() {
        Assert.assertArrayEquals(linkedList.toArray(new String[0]), new String[]{"1", "2", "3"});
    }

    @Test
    public void testIterator() {
        GenericIterator<String> genericIterator = linkedList.iterator();
        while (genericIterator.hasNext()) {
            genericIterator.next();
            genericIterator.remove();
        }
        Assert.assertArrayEquals(linkedList.toArray(), new String[]{});
    }
}
