package com.github.lhpmatlab.coding2017.basic; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/** 
* MyLinkedList Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 26, 2017</pre> 
* @version 1.0 
*/ 
public class MyLinkedListTest {
    private MyLinkedList<String> linkedList;

    @Before
    public void before() throws Exception {
        linkedList = new MyLinkedList<>();
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: add(T t)
    *
    */
    @Test
    public void testAddT() throws Exception {
        assertEquals("init list size is 0  ", linkedList.size(), 0);
        linkedList.add("1");
        linkedList.add("2");
        assertEquals("add list size ", linkedList.size(), 2);
        for (int i=0; i<linkedList.size(); i++) {
            assertEquals("index of"+i,linkedList.get(i),""+(i+1));
        }
    }

    /**
    *
    * Method: add(int index, T element)
    *
    */
    @Test
    public void testAddForIndexElement() throws Exception {
//        linkedList.add("1");
//        linkedList.add("3");
//        linkedList.add(1,"2");
//        assertEquals("add list size ", linkedList.size(), 3);
//        for (int i=0; i<linkedList.size(); i++) {
//            assertEquals("index of"+i,linkedList.get(i),""+(i+1));
//        }
    }

    /**
    *
    * Method: get(int index)
    *
    */
    @Test
    public void testGet() throws Exception {
        linkedList.add("1");
        linkedList.add("2");
        assertEquals("get methos; ",linkedList.get(0),"1" );
    }

    /**
    *
    * Method: set(int index, T newValue)
    *
    */
    @Test
    public void testSet() throws Exception {
        linkedList.add("1");
        linkedList.add("2");
        linkedList.set(1, "3");
        assertEquals("set mehtod ", linkedList.get(1),"3");
    }

    /**
    *
    * Method: remove(int index)
    *
    */
    @Test
    public void testRemove() throws Exception {
        linkedList.add("1");
        linkedList.add("2");
        linkedList.remove(0);
        assertEquals("remove methos", linkedList.size(),1);
    }

    /**
    *
    * Method: size()
    *
    */
    @Test
    public void testSize() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: isEmpty()
    *
    */
    @Test
    public void testIsEmpty() throws Exception {
        assertEquals("isEmpth method ", linkedList.isEmpty(),true);
    }


} 
