package liuxincourse;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {
	
	@Test
	public void testAdd() {
		LinkedList list=new LinkedList();
		list.add(33);
		list.add(44);
		list.add(55);
		list.add(88);
		assertEquals(88, list.get(3));
	}
	
	@Test
	public void testAddIndex() {
		LinkedList list=new LinkedList();
		list.add(33);
		list.add(44);
		list.add(55);
		list.add(1, 88);
		assertEquals(55, list.get(3));
	}
	
	@Test
	public void testAddFirst() {
		LinkedList list=new LinkedList();
		list.add(33);
		list.add(44);
		list.add(55);
		list.addFirst(88);
		assertEquals(88, list.get(0));
	}

	@Test
	public void testAddLast() {
		LinkedList list=new LinkedList();
		list.add(33);
		list.add(44);
		list.add(55);
		list.addFirst(88);
		list.addLast(00);
		assertEquals(00, list.get(list.size()-1));
	}
	
	@Test
	public void testRemoveFirst() {
		LinkedList list=new LinkedList();
		list.add(33);
		list.add(44);
		list.add(55);
		list.addFirst(88);
		assertEquals(88, list.removeFirst());
	}
	
	@Test
	public void testRemoveLast() {
		LinkedList list=new LinkedList();
		list.add(33);
		list.add(44);
		list.add(55);
		list.addFirst(88);
		assertEquals(55, list.removeLast());
	}
}
