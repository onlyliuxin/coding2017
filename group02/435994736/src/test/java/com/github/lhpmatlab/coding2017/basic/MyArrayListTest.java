package com.github.lhpmatlab.coding2017.basic;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MyArrayListTest {
	 private MyArrayList<String> list;

	    @Before
	    public void init(){
	        list = new MyArrayList<>();
	    }

	    @Test
	    public void testEnsureCapacity() {
	        assertEquals("init list size is 0  ", list.size(), 0);
	        list.add("1");
	        list.ensureCapacity(10);
	        assertEquals("ensureCapacity size is 10 ", list.size(),1);
	    }

	    /**
	     * 在列表的末尾添加元素
	     */
	    @Test
	    public void testAddT() {
	        assertEquals("init list size is 0  ", list.size(), 0);
	        list.add("1");
	        list.add("2");
	        assertEquals("add list size ", list.size(), 2);
	        for (int i=0; i<list.size(); i++) {
	            assertEquals("index of"+i,list.get(i),""+(i+1));
	        }
	    }

	    /**
	     * 测试在list的任意索引处添加元素
	     */
	    @Test
	    public void testAddIntT() {
	        assertEquals("init list size is 0  ", list.size(), 0);
	        list.add("1");
	        list.add("3");
	        list.add(1,"2");
	        assertEquals("add list size ", list.size(), 3);
	        assertEquals("add index 1 element is" ,list.get(1),"2");
	        for (int i=0; i<list.size(); i++) {
	            assertEquals("index of"+i,list.get(i),""+(i+1));
	        }
	    }

	    @Test
	    public void testDelete() {
	        assertEquals("init list size is 0  ", list.size(), 0);
	        list.add("1");
	        list.add("3");
	        list.add(1,"2");
	        assertEquals("add list size ", list.size(), 3);
	        list.delete(1);
	        assertEquals("after delete index 1 ",list.get(1),"3");
	    }

	    @Test
	    public void testGet() {
	        assertEquals("init list size is 0  ", list.size(), 0);
	        list.add("1");
	        list.add("2");
	        list.add("3");
	        for(int i=0; i<list.size();i++) {
	            assertEquals("index of"+i,list.get(i),""+(i+1));
	        }
	    }

	    @Test
	    public void testSet() {
	        assertEquals("init list size is 0  ", list.size(), 0);
	        list.add("1");
	        list.add("3");
	        list.add("3");
	        list.set(1, "2");
	        for(int i=0; i<list.size();i++) {
	            assertEquals("index of"+i,list.get(i),""+(i+1));
	        }
	    }

	    @Test
	    public void testSize() {
	        assertEquals("init list size is 0  ", list.size(), 0);
	        list.add("1");
	        list.add("3");
	        list.add("3");
	        assertEquals("after add list size is ", list.size(),3);
	    }

	    /**
	     * 正确的判空用例
	     */
	    @Test
	    public void testIsEmpty() {
	       assertEquals("list is empty", list.isEmpty(),true);
	    }

	    /**
	     * 失败的判空用例
	     */
	    @Test
	    public void testIsEmptyNot() {
	        list.add("1");
	        assertEquals("list is empty", list.isEmpty(),false);
	    }
}
