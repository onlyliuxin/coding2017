package com.github.ipk2015.coding2017.basic.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.github.ipk2015.coding2017.basic.Iterator;
import com.github.ipk2015.coding2017.basic.linkedlist.LinkedList;


public class LinkedListTest {
	LinkedList list;
	@Before
	public void setUp() throws Exception {
		list=new LinkedList(); 
	}
	private static String toString(LinkedList list){
		Iterator iterator = list.iterator();
		StringBuilder builder=new StringBuilder();
		int next;
		while(iterator.hasNext()){
			next = (Integer)iterator.next();
			builder.append(next+",");
		}
		return builder.toString();
	}
	@Test
	public void testAddObject() {
		list.add("hehe1");
		list.add("hehe2");
		assertEquals("hehe2", list.get(1));
	}

	@Test
	public void testAddIntObject() {
		list.add("hehe1");
		list.add("hehe2");
		list.add("hehe3");
		list.add(1,"arm");
		assertEquals("arm", list.get(1));
	}

	@Test
	public void testGet() {
		list.add("hehe1");
		list.add("hehe2");
		assertEquals("hehe2", list.get(1));
	}

	@Test
	public void testRemoveInt() {
		list.add("hehe1");
		list.add("hehe2");
		list.add("hehe3");
		list.remove(1);
		assertEquals(2, list.size());
	}

	@Test
	public void testSize() {
		list.add("hehe1");
		list.add("hehe2");
		list.add("hehe3");
		assertEquals(3, list.size());
	}

	@Test
	public void testAddFirst() {
		list.add("hehe1");
		list.add("hehe2");
		list.add("hehe3");
		list.addFirst("arm");
		assertEquals("arm", list.get(0));
	}

	@Test
	public void testAddLast() {
		list.add("hehe1");
		list.add("hehe2");
		list.add("hehe3");
		list.addLast("arm");
		assertEquals("arm", list.get(list.size()-1));
	}

	@Test
	public void testRemoveFirst() {
		list.add("hehe1");
		list.add("hehe2");
		list.add("hehe3");
		list.removeFirst();
		assertEquals("hehe2", list.get(0));
	}
	@Test
	public void testRemoveLast() {
		list.add("hehe1");
		list.add("hehe2");
		list.add("hehe3");
		list.removeLast();
		assertEquals("hehe2", list.get(list.size()-1));
	}
	@Test
	public void testReverse() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.reverse();
		assertEquals("4,3,2,1,", toString(list));
	}

	@Test
	public void testRemoveFirstHalf() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.removeFirstHalf();
		assertEquals("3,4,5,", toString(list));
	}

	@Test
	public void testRemoveIntInt() {
		list.add(1);
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(9);
		list.add(10);
		list.add(11);
		list.remove(3, 2);
		assertEquals("1,3,5,10,11,", toString(list));
		list.remove(0, 3);
		assertEquals("10,11,", toString(list));
	}

	@Test
	public void testGetElements() {
		list.add(11);
		list.add(101);
		list.add(201);
		list.add(301);
		list.add(401);
		list.add(501);
		list.add(601);
		list.add(701);
		LinkedList mList=new LinkedList();
		mList.add(1);
		mList.add(3);
		mList.add(4);
		mList.add(6);
		int[] elements = list.getElements(mList);
		assertEquals("[101,301,401,601]", Arrays.toString(elements).replaceAll(" ", ""));
	}

	@Test
	public void testSubtract() {
		list.add(11);
		list.add(101);
		list.add(201);
		list.add(301);
		list.add(401);
		list.add(501);
		list.add(601);
		list.add(701);
		LinkedList mList=new LinkedList();
		mList.add(101);
		mList.add(301);
		mList.add(501);
		mList.add(601);
		list.subtract(mList);
		assertEquals("11,201,401,701,", toString(list));
	}

	@Test
	public void testRemoveDuplicateValues() {
		list.add(11);
		list.add(11);
		list.add(101);
		list.add(101);
		list.add(101);
		list.add(201);
		list.add(301);
		list.add(301);
		list.add(401);
		list.add(401);
		list.add(501);
		list.removeDuplicateValues();
		assertEquals("11,101,201,301,401,501,", toString(list));
	}

	@Test
	public  void testRemoveRange() {
		list.add(11);
		list.add(101);
		list.add(201);
		list.add(301);
		list.add(401);
		list.add(501);
		list.add(601);
		list.add(701);
		list.add(801);
		list.add(901);
		list.add(1001);
		
		list.removeRange(801, 1101);
		assertEquals("11,101,201,301,401,501,601,701,", toString(list));
		list.removeRange(100, 400);
		assertEquals("11,401,501,601,701,", toString(list));
		list.removeRange(1, 500);
		assertEquals("501,601,701,", toString(list));
		list.removeRange(700, 900);
		assertEquals("501,601,", toString(list));
		list.removeRange(1,200);
		assertEquals("501,601,", toString(list));
		list.removeRange(700, 900);
		assertEquals("501,601,", toString(list));
		list.removeRange(1, 700);
		assertEquals("", toString(list));
	}

	@Test
	public void testRsection() {
		list.add(11);
		list.add(21);
		list.add(31);
		LinkedList mList=new LinkedList();
		mList.add(1);
		mList.add(2);
		mList.add(11);
		mList.add(25);
		mList.add(35);
		mList=list.rsection(mList);
		assertEquals("1,2,11,11,21,25,31,35,", toString(mList));
	}

	

}
