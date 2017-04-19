package com.m0312.download;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.m0226.basic.Iterator;
import com.m0312.download.api.LinkedList;

public class LinkedListTest {

	LinkedList list;
	@Before
	public void setUp() throws Exception {
		list=new LinkedList();
	}
	
	public static void traverse(LinkedList list){
		Iterator ite=list.iterator();
		while(ite.hasNext()){
			System.out.print(ite.next()+",");
		}
		System.out.println("===end===");
	}

	@Test
	public void testReverse() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		traverse(list);
		list.reverse();
		traverse(list);
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveFirstHalf() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		traverse(list);
		list.removeFirstHalf();
		traverse(list);
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		traverse(list);
		list.remove(1,2);//145
		traverse(list);
		fail("Not yet implemented");
	}
	@Test
	public void testRemoveIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetElements() {
		/* 例如当前链表 = 11->101->201->301->401->501->601->701
		 * listB = 1->3->4->6
		 * 返回的结果应该是[101,301,401,601]  */
		list.add(0);
		list.add(1);
		list.add(222);
		list.add(3);
		list.add(444);
		list.add(5);
//		traverse(list);
		LinkedList listindex=new LinkedList();
		listindex.add(2);
		listindex.add(4);
		int[] result=list.getElements(listindex);//0135
		for (int i : result) {
			System.out.println(i);
		}
		
		
		fail("Not yet implemented");
	}

	@Test
	public void testSubtract() {
		list.add(0);
		list.add(1);
		list.add(222);
		list.add(222);
		list.add(3);
		list.add(444);
		list.add(5);
		traverse(list);
		LinkedList listindex=new LinkedList();
		listindex.add(222);
		listindex.add(5);
		list.subtract(listindex);
		traverse(list);
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveDuplicateValues() {
		list.add(3);
		list.add(1);
		list.add(22);
		list.add(22);
		list.add(44);
		list.add(5);
		list.add(6);
		traverse(list);
		list.removeDuplicateValues();
		traverse(list);
		System.out.println("size : "+list.size());
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveRange() {
		fail("Not yet implemented");
	}

	@Test
	public void testIntersection() {
		fail("Not yet implemented");
	}

}
