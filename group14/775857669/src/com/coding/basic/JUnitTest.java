package com.coding.basic;

import static org.junit.Assert.*;


import org.junit.Test;

public class JUnitTest {
	@Test
	public void testArrayList() {
		ArrayList list = new ArrayList();
		for (int i = 0; i < 300; i++) {
			list.add(i);
		}
		assertTrue(list.size() == 300);
		list.add(3, 3);
		assertTrue( (int)list.get(3) == 3 );
		assertTrue( (int)list.get(2) == 2 );
		assertTrue( (int)list.get(4) == 3 );
		assertTrue( (int)list.get(299) == 298 );
		assertTrue( (int)list.get(300) == 299 );
		assertTrue(list.size() == 301);
		list.remove(3);
		assertTrue( (int)list.get(3) == 3 );
		assertTrue( (int)list.get(2) == 2 );
		assertTrue( (int)list.get(4) == 4 );
		assertTrue( (int)list.get(299) == 299 );
		assertTrue(list.size() == 300);
		Iterator iterator = list.iterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		System.out.println();
		LinkedList linkedList = new LinkedList();
		for(int i=0 ; i<10 ; i++){
			linkedList.add(i);
		}
		assertTrue(linkedList.size() == 10);
		Iterator iterator2 = linkedList.iterator();
		while(iterator2.hasNext()) {
			System.out.print(iterator2.next() + " ");
		}
		linkedList.add(0, -1);
		linkedList.add(11,10);
		
		assertTrue(linkedList.size() == 12);
		assertTrue((int)linkedList.removeFirst() == -1);
		
		assertTrue((int)linkedList.removeLast() == 10);
		assertTrue((int)linkedList.remove(5) == 5);
		assertTrue(linkedList.size() == 9);
		
		Stack stack = new Stack();
		for (int i = 0; i < 10; i++) {
			stack.push(i);
		}
		assertTrue(stack.size() == 10);
		assertFalse(stack.isEmpty());
		assertTrue((int)stack.peek() == 9);
		assertTrue((int)stack.pop() == 9);
		assertTrue(stack.size() == 9);
		System.out.println();
		for (int i=0 ; i<9 ; i++){
			System.out.print(stack.pop() + " ");
		}
		assertTrue(stack.isEmpty());
	}

}
