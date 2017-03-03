package com.coding.basic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by songbao.yang on 2017/2/21.
 *
 */
public class ArrayListTest {

    private ArrayList list;

    public static final int SIZE = 10000;

    @Before
    public void setUp() throws Exception {

        list = new ArrayList();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void add() throws Exception {

        for (int i = 0; i < SIZE; i++) {
            list.add(i);
            Assert.assertEquals(i+1, list.size());
        }
    }

    @Test
    public void add1() throws Exception {

        add();
        for (int i = 0; i < 1000; i++) {
            int oldSize = list.size();
            int randomIndex = (int)Math.floor(Math.random() * oldSize);

            list.add(randomIndex, randomIndex);
            int newSize = list.size();

            Assert.assertEquals(newSize, oldSize+1);
            Assert.assertEquals(list.get(randomIndex), randomIndex);
        }
    }

    @Test
    public void get() throws Exception {

        add();
        for (int i = 0; i < SIZE * 100; i++) {
            int randomIndex = (int)Math.floor(Math.random() * list.size());
            if(randomIndex < 0 || randomIndex >= SIZE){
                System.out.println("illegal index: " + randomIndex);
                throw new RuntimeException();
            }
            int o = (Integer) list.get(randomIndex);
            Assert.assertEquals(randomIndex, o);
        }
    }

    @Test
    public void remove() throws Exception {

        add();
        for (int i = 0; i < SIZE; i++) {
            System.out.println("remove: " + i);
            list.remove(0);
        }

    }

    @Test
    public void size() throws Exception {

    }

    @Test
    public void iterator() throws Exception {
        add();
        Iterator iterator1 = list.iterator();
        int i = 0;
        while (iterator1.hasNext()){
            Object next = iterator1.next();
            Assert.assertEquals(i, next);
            i++;
        }
    }

}