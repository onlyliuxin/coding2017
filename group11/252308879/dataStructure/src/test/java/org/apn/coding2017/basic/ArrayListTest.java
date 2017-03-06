package org.apn.coding2017.basic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pan on 2017/2/26.
 */
public class ArrayListTest {

    ArrayList arrayList;

    @Before
    public void  before(){
        arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
    }

    @Test
    public void add() throws Exception {
        ArrayList arrayList = new ArrayList();
        arrayList.add(3);
        System.out.println(arrayList);
    }

    @Test
    public void set() throws Exception {
        arrayList.add(3);
        arrayList.set(0, 4);
        System.out.println(arrayList);
    }

    @Test
    public void get() throws Exception {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        Object o = arrayList.get(1);
        System.out.println(o);
    }

    @Test
    public void remove() throws Exception {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.remove(1);
        System.out.println(arrayList);
    }

    @Test
    public void size() throws Exception {
        System.out.println(arrayList.size());
    }

    @Test
    public void isEmpty() throws Exception {
        System.out.println(arrayList.isEmpty());
    }

    @Test
    public void iterator() throws Exception {
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
            iterator.remove();
        }
        System.out.println(arrayList.isEmpty());
    }

}