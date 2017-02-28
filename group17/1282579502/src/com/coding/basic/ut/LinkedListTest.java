package com.coding.basic.ut;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coding.basic.Iterator;
import com.coding.basic.LinkedList;

public class LinkedListTest {

	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testGet() {
		LinkedList ll = new LinkedList();
		ll.add(new String("0"));
		assertEquals(ll.get(0), "0");
	}
	
	@Test
	public void testAddObject() {
		LinkedList ll = new LinkedList();
		String[] items = new String[]{"0","1","2","3","4"};
		for(String item : items){
			ll.add(item);
		}
		for(int i = 0; i<items.length; i++){
			assertEquals(items[i], ll.get(i));
		}
	}

	@Test
	public void testAddIntObject() {
		LinkedList ll = new LinkedList();
		String[] items = new String[]{"0","1","2","3","4"};
		for(String item : items){
			ll.add(0, item);
		}
		for(int i = 0; i<items.length; i++){
			assertEquals(items[items.length-1 -i], ll.get(i));
		}
	}



	@Test
	public void testRemove() {
		LinkedList ll = new LinkedList();
		String[] items = new String[]{"0","1","2","3","4"};
		for(int i = 0 ; i< items.length; i++){
			ll.add(i, items[i] );
		}
		Object o = ll.remove(0);
		assertEquals(o, "0");
		o = ll.remove(ll.size() - 1);
		assertEquals(o, "4");
		o = ll.remove(1);
		assertEquals(o, "2");
	}

	@Test
	public void testSize() {
		LinkedList ll = new LinkedList();
		assertEquals(0, ll.size());
		ll.add("0");
		assertEquals(1, ll.size());
		ll.add("1");
		assertEquals(2, ll.size());
		ll.remove(1);
		assertEquals(1, ll.size());
		ll.remove(0);
		assertEquals(0, ll.size());
		
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testIndexOutOfBoundsException() {
		LinkedList ll = new LinkedList();
		ll.add(2, "1");
	}

	@Test
	public void testAddFirst() {
		LinkedList ll = new LinkedList();
		String[] items = new String[]{"0","1","2","3","4"};
		for(int i = 0 ; i< items.length; i++){
			ll.addFirst(items[i] );
		}
		
		for(int i = ll.size() -1 ; i>=0; i--){
			assertEquals(ll.get(i), items[items.length - 1 -i]);
		}
		
	}

	@Test
	public void testAddLast() {
		LinkedList ll = new LinkedList();
		String[] items = new String[]{"0","1","2","3","4"};
		for(int i = 0 ; i< items.length; i++){
			ll.addLast(items[i] );
		}
		//expect 0, 1, 2, 3, 4
		for(int i = 0; i<ll.size(); i ++){
			assertEquals(items[i], ll.get(i));
		}
	}

	@Test
	public void testRemoveFirst() {
		LinkedList ll = new LinkedList();
		String[] items = new String[]{"0","1","2","3","4"};
		for(int i = 0 ; i< items.length; i++){
			ll.addLast(items[i] );
		}
		
		Object o = ll.removeFirst();
		assertEquals("0", o);
		for(int i = 0; i< ll.size(); i++){
			assertEquals(items[i+1],ll.get(i));
		}
		assertEquals(4, ll.size());
	}

	@Test
	public void testRemoveLast() {
		LinkedList ll = new LinkedList();
		String[] items = new String[]{"0","1","2","3","4"};
		for(int i = 0 ; i< items.length; i++){
			ll.addLast(items[i] );
		}
		
		Object o = ll.removeLast();
		assertEquals(o, "4");
		for(int i = 0; i< ll.size(); i++){
			assertEquals(items[i],ll.get(i));
		}
		assertEquals(4, ll.size());
	}

	@Test
	public void testIterator() {
		LinkedList ll = new LinkedList();
		String[] items = new String[]{"0","1","2","3","4"};
		for(int i = 0 ; i< items.length; i++){
			ll.add(items[i] );
		}
		int count = 0;
		Iterator itr = ll.iterator();
		while(itr.hasNext()){
			Object o = itr.next();
			assertEquals(items[count], o);
			count++;
		}
	}

}
