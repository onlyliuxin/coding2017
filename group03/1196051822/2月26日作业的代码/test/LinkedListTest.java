package com.byhieg.coding2017test;

import com.byhieg.coding2017.Iterator;
import com.byhieg.coding2017.LinkedList;
import com.sun.org.apache.bcel.internal.generic.INEG;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/2/22.
 * Mail to byhieg@gmail.com
 */
public class LinkedListTest extends TestCase {
    private LinkedList list = new LinkedList();
    public void testAdd() throws Exception {
        list.add(null);
        list.add(-1);
        list.add(-2);
        list.add(0x5);
        list.add(true);
        list.add("123");
        list.add(Integer.MAX_VALUE + 100000);

    }

    public void testAdd1() throws Exception {
//        list.add(-1,100);
//        list.add(20,111);
        list.add(0,11);
        list.add(1,"sad");
        list.add(2,"fas");

    }

    public void testGet() throws Exception {
        for (int i = 0 ; i < 10 ; i++) {
            list.add(i,i + "");
        }

        for (int i = 0 ;i < list.size();i++) {
            System.out.println(list.get(i));
        }
    }

    public void testRemove() throws Exception {
        for (int i = 0 ; i < 10 ; i++) {
            list.add(i,i + "");
        }

        for (int i = 0 ; i < list.size() ; i++) {
            list.remove(i);
        }

        for (int i = 0 ;i < list.size();i++) {
            System.out.println(list.get(i));
        }
    }


    public void testAddFirst() throws Exception {
        list.addFirst("byhieg");
        list.addFirst("123412");
        list.addFirst("byhaieg");
        list.addFirst("byhfadas12ieg");
        list.addFirst("fas");
        for (int i = 0 ; i < list.size();i++) {
            System.out.println(list.get(i));
        }
    }

    public void testAddLast() throws Exception {
        list.addLast("asga");
        list.addLast("124");
        list.addLast("fasd");
        list.addLast("fas");
        list.addLast("gasd2");

        for (int i = 0 ; i < list.size();i++) {
            System.out.println(list.get(i));
        }

    }

    public void testRemoveFirst() throws Exception {
        list.addFirst("byhieg");
        list.addFirst("123412");
        list.addFirst("byhaieg");
        list.addFirst("byhfadas12ieg");
        list.addFirst("fas");
        for (int i = 0 ; i < list.size();i++) {
            list.removeLast();
        }

        System.out.println(list.size());
    }

    public void testRemoveLast() throws Exception {
        list.addLast("asga");
        list.addLast("124");
        list.addLast("fasd");
        list.addLast("fas");
        list.addLast("gasd2");
        for (int i = 0 ; i < list.size();i++) {
            list.removeFirst();
        }

        System.out.println(list.size());
    }

    public void testIterator() throws Exception {
        list.addLast("asga");
        list.addLast("124");
        list.addLast("fasd");
        list.addLast("fas");
        list.addLast("gasd2");

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}