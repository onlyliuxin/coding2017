package com.byhieg.coding2017test;

import com.byhieg.coding2017.ArrayList;
import com.byhieg.coding2017.Iterator;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/2/22.
 * Mail to byhieg@gmail.com
 */
public class ArrayListTest extends TestCase {
    ArrayList arrayList = new ArrayList();

    public void testAdd() throws Exception {
        arrayList.add(1);
        arrayList.add(null);
        arrayList.add(-1);
        arrayList.add("1");
        arrayList.add(true);
        arrayList.add(Integer.MAX_VALUE);
        arrayList.add(Integer.MIN_VALUE);


    }

    public void testAdd1() throws Exception {
//        arrayList.add(-1,0);
//        arrayList.add(100,0);
        arrayList.add(0,2);
        arrayList.add(1,10);
        arrayList.add(2,111);
    }

    public void testGet() throws Exception {
        for (int i = 0; i < 10 ; i++) {
            arrayList.add(i);
        }

        for (int i = 0 ; i < 10 ; i++) {
            System.out.println(arrayList.get(i));
        }
    }

    public void testRemove() throws Exception {
        for (int i = 0; i < 10 ; i++) {
            arrayList.add(i);
        }

        for (int i = 0 ; i < 10 ; i++) {
            System.out.println(arrayList.get(i));
        }

        for (int i = 0 ; i < 10 ; i++) {
            arrayList.remove(9 - i);
        }

        for (int i = 0 ; i < arrayList.size() ; i++) {
            System.out.println(arrayList.get(i));
        }
    }

    public void testSize() throws Exception {
        for (int i = 0; i < 10 ; i++) {
            arrayList.add(i);
        }
        System.out.println(arrayList.size());
    }

    public void testIterator() throws Exception {
        for (int i = 0; i < 10 ; i++) {
            arrayList.add(i);
        }

        System.out.println("开始测试Iterator");
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}