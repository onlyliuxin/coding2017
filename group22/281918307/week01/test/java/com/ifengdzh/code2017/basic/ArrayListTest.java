package com.ifengdzh.code2017.basic;

import static org.junit.Assert.*;

/**
 * Created by ajaxfeng on 2017/4/4.
 */
public class ArrayListTest extends ArrayList {

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testAdd() throws Exception {
        List arrayList=new ArrayList();

        assertEquals(true,arrayList.isEmpty());
        assertEquals(0,arrayList.size());

        for(int i=0;i<100;i++){
            arrayList.add(i);
            if(i>0){
                Integer o = (Integer) arrayList.get(i);
                int target=o.intValue();
                assertEquals(i,target);
            }
        }

        assertEquals(false,arrayList.isEmpty());


        arrayList.add(0,111);
        assertEquals(111,arrayList.get(0));


        arrayList.add(101,222);
        assertEquals(222,arrayList.get(101));

        assertEquals(102,arrayList.size());

        arrayList.remove(0);
        assertEquals(0,arrayList.get(0));

        arrayList.remove(101);
        assertEquals(99,arrayList.get(99));


        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
        }


    }

    @org.junit.Test
    public void testAdd1() throws Exception {

    }

    @org.junit.Test
    public void testRemove() throws Exception {

    }

    @org.junit.Test
    public void testGet() throws Exception {

    }
}