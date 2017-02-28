package com.coding.week1;

import static org.junit.Assert.*;


import org.junit.Test;

public class TestLinkedList {
	LinkedList linkedList = new LinkedList(); 
	
	@Test
	public void test() {
		//add
		linkedList.add("AA");
		linkedList.add(0,"BB");
		linkedList.add(1,"CC");
		linkedList.add(3,"DD");	
		
		assertEquals(linkedList.get(0), "BB");
		assertEquals(linkedList.get(1), "CC");
		assertEquals(linkedList.get(2), "AA");
		assertEquals(linkedList.last(), "DD");
		//add first last
		linkedList.addFirst("EE");
		assertEquals(linkedList.get(0), "EE");
		linkedList.addLast("FF");
		assertEquals(linkedList.get(5), "FF");
		//remove
		assertEquals(linkedList.remove(1), "BB");
		assertEquals(linkedList.removeFirst(), "EE");
		assertEquals(linkedList.removeLast(), "FF");
		//iterator
		Iterator ito = linkedList.iterator();
		int i=0;
		while(ito.hasNext()){
			assertEquals(linkedList.get(i), ito.next());
			i++;
		}
		assertEquals(i, linkedList.size());
	}

}
