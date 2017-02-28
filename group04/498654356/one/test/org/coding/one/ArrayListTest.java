package org.coding.one;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {

	private ArrayList target;
	private int size = 15;
	
	@Before
	public void setUp() throws Exception {
		this.target = new ArrayList();
	}

	@After
	public void tearDown() throws Exception {
		this.target = null;
	}

	@Test
	public void testAddObject() {
		addElement(size);
		Assert.assertFalse(target.isEmpty());
		Assert.assertEquals(size, target.size());
		for(int i = 0; i < size; i++) {
			Assert.assertEquals(i, ((Integer)target.get(i)).intValue());
		}
//		System.out.println(target);
	}

	private void addElement(int size) {
		for(int i = 0; i < size; i++) {
			target.add(i);
		}
	}
	
	@Test
	public void testAddIntObject() {
		addElement(size);
		int destIndex = 1;
		int destVal = 11;
		target.add(destIndex, destVal);
		Assert.assertEquals(destVal, target.get(destIndex));
		Assert.assertEquals(size + 1, target.size());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddIntObjectException() {
		target.add(1, 5);
	}

	@Test
	public void testGet() {
		addElement(size);
		Assert.assertEquals(1, ((Integer)target.get(1)).intValue());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetException() {
		addElement(size);
		target.get(size);
	}

	@Test
	public void testRemove() {
		addElement(size);
		int val = (int) target.remove(0);
		Assert.assertEquals(0, val);
		Assert.assertEquals(size -1, target.size());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveException() {
		addElement(size);
		target.remove(size);
	}

	@Test
	public void testSize() {
		Assert.assertEquals(0, target.size());
		addElement(size);
		Assert.assertEquals(size, target.size());
	}

	@Test
	public void testIsEmpty() {
		Assert.assertTrue(target.isEmpty());
		addElement(size);
		Assert.assertFalse(target.isEmpty());
	}

}
