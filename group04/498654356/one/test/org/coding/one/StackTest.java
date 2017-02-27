package org.coding.one;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class StackTest {
	
	private Stack target;

	@Before
	public void setUp() throws Exception {
		target = new Stack();
	}

	@After
	public void tearDown() throws Exception {
		target = null;
	}

	@Test
	public void testPush() {
		Assert.assertEquals(0, target.size());
		target.push(1);
		Assert.assertEquals(1, target.peek());
		Assert.assertEquals(1, target.size());
	}

	@Test
	public void testPop() {
		target.push(1);
		target.push(2);
		Assert.assertEquals(2, target.pop());
		Assert.assertEquals(1, target.size());
		Assert.assertEquals(1, target.pop());
		Assert.assertEquals(0, target.size());
	}

	@Test
	public void testPeek() {
		target.push(1);
		target.push(2);
		Assert.assertEquals(2, target.peek());
		Assert.assertEquals(2, target.size());
		Assert.assertEquals(2, target.peek());
		Assert.assertEquals(2, target.size());
	}

	@Test
	public void testIsEmpty() {
		Assert.assertTrue(target.isEmpty());
		target.push(1);
		Assert.assertFalse(target.isEmpty());
		target.pop();
		Assert.assertTrue(target.isEmpty());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(0, target.size());
		target.push(1);
		Assert.assertEquals(1, target.size());
		target.pop();
		Assert.assertEquals(0, target.size());
	}

}
