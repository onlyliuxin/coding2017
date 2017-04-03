package com.circle.collection;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by keweiyang on 2017/2/25.
 */
public class ArrayListTest {
    private ArrayList list = null;

//    @Test
    public void add() throws Exception {
        list = new ArrayList(3);
        list.add(1);
        list.add(2);
        list.add(3);
        //测试自动扩容
        list.add(4);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

//    @Test
    public void add1() throws Exception {
        list = new ArrayList(10);
        list.add(1);
        list.add(2);
        list.add(3);
        //测试自动扩容
        list.add(4);
        list.add(5, 5);
        list.add(4, 6);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }

//    @Test
    public void get() throws Exception {
        list = new ArrayList(10);
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println(list.get(1));

    }

//    @Test
    public void remove() throws Exception {
        list = new ArrayList(10);
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println(list.remove(1));
        System.out.println("------------>");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void size() throws Exception {
        list = new ArrayList(10);
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println("size= "+list.size());
    }

}