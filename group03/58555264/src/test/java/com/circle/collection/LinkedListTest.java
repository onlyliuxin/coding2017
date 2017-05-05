package com.circle.collection;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by keweiyang on 2017/2/25.
 */
public class LinkedListTest {
    private LinkedList list = null;

//    @Test
    public void add() throws Exception {

        list = new LinkedList();

        list.add(1);
        list.add(2);

        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }
    }

    @Test
    public void add1() throws Exception {
        list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(1, 3);

        list.add(3, 4);
        //索引不正确情况
        list.add(6,6);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }

    }

    @Test
    public void get() throws Exception {
        list = new LinkedList();
        list.add(1);
        list.add(2);

        System.out.println(list.get(0));
        System.out.println(list.get(1));

    }

    @Test
    public void remove() throws Exception {
        list = new LinkedList();
        list.add(1);
        list.add(2);
        list.remove(1);

        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }


    }

    @Test
    public void size() throws Exception {
        list = new LinkedList();
        list.add(1);
        list.add(2);
        System.out.println("size= "+ list.size());
    }

    @Test
    public void addFirst() throws Exception {
        list = new LinkedList();
        list.add(1);
        list.add(2);
        list.addFirst("123");


        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }

        list.remove(0);
        System.out.println("---------------");;
        it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }
    }

    @Test
    public void addLast() throws Exception {
        list = new LinkedList();
        list.addFirst(1);
        list.addLast(2);
        list.add(3);
        list.addLast(4);
        list.addFirst(0);

        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }

    }

    @Test
    public void removeFirst() throws Exception {

        list = new LinkedList();
        list.addFirst(1);
        list.addLast(2);
        list.add(3);
        list.addLast(4);
        list.addFirst(0);
        list.removeFirst();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }



    @Test
    public void removeLast() throws Exception {
        list = new LinkedList();
        list.addFirst(1);
        list.addLast(2);
        list.add(3);
        list.addLast(4);
        list.addFirst(0);
        list.removeLast();
        list.removeLast();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void iterator() throws Exception {

    }

}