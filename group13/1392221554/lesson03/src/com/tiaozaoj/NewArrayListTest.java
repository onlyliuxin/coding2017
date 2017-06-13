package com.tiaozaoj;

import java.awt.List;

import junit.framework.TestCase;

public class NewArrayListTest extends TestCase {
	private NewArrayList list = new NewArrayList();
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAddObject() {
		list.add("0");
		list.add("2");
		assertEquals("0", list.get(0));
		assertEquals("2", list.get(1));
	}

	public void testAddIntObject() {
		list.add(0,"0");
		assertEquals("0", list.get(0));
	}

	public void testRemove() {
		list.add(0,"0");
		list.add(1,"1");
		list.add(2,"2");
		assertEquals("1", list.remove(1));
	}

	public void testSize() {
		list.add(0,"0");
		list.add(1,"1");
		list.add(2,"2");
		assertEquals(3, list.size());
	}

	public void testIterator() {
		int i = 0;
		for(NewIterator It = list.iterator();It.hasNext();){
			Object str = (Object) It.next();
			assertEquals(list.get(i++), str);
		}
		
		int j = list.size();
		for(NewIterator It = list.iterator();It.hasNext();){
			It.next();
			It.remove();
			assertEquals(--j, list.size());
		}
	}
}
