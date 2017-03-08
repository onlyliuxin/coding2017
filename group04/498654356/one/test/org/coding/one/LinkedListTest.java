package org.coding.one;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	
	private LinkedList target;
	private int size = 5;

	@Before
	public void setUp() throws Exception {
		this.target = new LinkedList();
	}

	@After
	public void tearDown() throws Exception {
		this.target = null;
	}

	@Test
	public void testAddObject() {
		target.add(1);
		Assert.assertEquals(1, ((Integer)target.get(0)).intValue());
		Assert.assertEquals(1, target.size());
	}

	@Test
	public void testAddIntObjectFirst() {
		target.add(0, 1);
		Assert.assertEquals(1, ((Integer)target.get(0)).intValue());
		Assert.assertEquals(1, target.size());
	}
	
	@Test
	public void testAddIntObject() {
		addElement();
		target.add(2, 22);
		Assert.assertEquals(22, ((Integer)target.get(2)).intValue());
		Assert.assertEquals(size + 1, target.size());
	}

	private void addElement() {
		for(int i = 0; i < size; i++) {
			target.add(i);
		}
	}

	@Test
	public void testAddIntObjectLast() {
		addElement();
		target.add(size, 100);
		Assert.assertEquals(100, ((Integer)target.get(size)).intValue());
		Assert.assertEquals(size + 1, target.size());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddIntObjectException() {
		target.add(-1, 3);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddIntObjectException2() {
		addElement();
		target.add(size + 1, 100);
	}
	
	
	@Test
	public void testGet() {
		addElement();
		Assert.assertEquals(4, target.get(4));
	}
	
	@Test
	public void testGetFirst() {
		addElement();
		Assert.assertEquals(0, target.get(0));
	}	
	
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetException() {
		addElement();
		target.get(size);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetException2() {
		addElement();
		target.get(-1);
	}
	
	@Test
	public void testRemove() {
		addElement();
		int dest = (int) target.remove(2);
		Assert.assertEquals(2, dest);
		Assert.assertEquals(4, target.size());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testEmptyObjRemove() {
		target.remove(0);
	}
	
	@Test
	public void testRemoveFirst2() {
		addElement();
		int dest = (int) target.remove(0);
		Assert.assertEquals(0, dest);
		Assert.assertEquals(4, target.size());
	}
	
	@Test
	public void testRemoveLast2() {
		addElement();
		int dest = (int) target.remove(size - 1);
		Assert.assertEquals(4, dest);
		Assert.assertEquals(4, target.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveException() {
		addElement();
		target.remove( - 1);
	}
	

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveException2() {
		addElement();
		target.remove(size);
	}
	
	@Test
	public void testSize() {
		Assert.assertEquals(0, target.size());
		addElement();
		Assert.assertEquals(size, target.size());
	}

	@Test
	public void testIsEmpty() {
		Assert.assertTrue(target.isEmpty());
		addElement();
		Assert.assertFalse(target.isEmpty());
	}

	@Test
	public void testAddFirst() {
		addElement();
		target.addFirst(100);
		Assert.assertEquals(100, target.get(0));
	}

	@Test
	public void testAddLast() {
		addElement();
		target.addLast(100);
		Assert.assertEquals(100, target.get(size));
	}

	@Test
	public void testRemoveFirst() {
		addElement();
		int dest = (int) target.removeFirst();
		Assert.assertEquals(0, dest);
		Assert.assertEquals(size - 1 , target.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveFirstException() {
		target.removeFirst();
	}
	
	@Test
	public void testRemoveLast() {
		addElement();
		int dest = (int) target.removeLast();
		Assert.assertEquals(4, dest);
		Assert.assertEquals(size - 1, target.size());
	}

	@Test
	public void testRemoveLast_one() {
		target.add(4);
		int dest = (int) target.removeLast();
		Assert.assertEquals(4, dest);
		Assert.assertEquals(0, target.size());
	}
}
