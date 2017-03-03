package org.apn.coding2017.basic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pan on 2017/2/26.
 */
public class LinkedListTest {

    LinkedList linkedList;

    @Before
    public void setUp() throws Exception {
        linkedList = new LinkedList();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
    }

    @Test
    public void add() throws Exception {
        linkedList.add(0);
        System.out.println(linkedList);
    }

    @Test
    public void get() throws Exception {
        Object o = linkedList.get(1);
        System.out.println(o);
    }

    @Test
    public void remove() throws Exception {
        linkedList.remove(1);
        System.out.println(linkedList);
    }

    @Test
    public void size() throws Exception {
        System.out.println(linkedList.size());
    }

    @Test
    public void addFirst() throws Exception {
        linkedList.addFirst(4);
        System.out.println(linkedList);
    }

    @Test
    public void addLast() throws Exception {
        linkedList.addLast(5);
        System.out.println(linkedList);
    }

    @Test
    public void removeFirst() throws Exception {
        linkedList.removeFirst();
        System.out.println(linkedList);
    }

    @Test
    public void removeLast() throws Exception {
        linkedList.removeLast();
        System.out.println(linkedList);
    }

    @Test
    public void iterator() throws Exception {
        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
            iterator.remove();
        }
        System.out.println(linkedList);
    }

}