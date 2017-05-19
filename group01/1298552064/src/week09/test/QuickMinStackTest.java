package week09.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week09.basic.QuickMinStack;

public class QuickMinStackTest {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		QuickMinStack stack = new QuickMinStack();
		stack.push(3);
		stack.push(5);
		stack.push(2);
		stack.push(8);
		stack.push(1);
		stack.push(6);
		
		Assert.assertEquals(1, stack.findMin());

		Assert.assertEquals(6, stack.pop());
		Assert.assertEquals(1, stack.pop());
		
		Assert.assertEquals(2, stack.findMin());
		
		Assert.assertEquals(8, stack.pop());
		Assert.assertEquals(2, stack.pop());
		
		Assert.assertEquals(3, stack.findMin());
	}
	
}
