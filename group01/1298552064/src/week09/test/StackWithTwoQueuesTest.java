package week09.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week09.basic.StackWithTwoQueues;

public class StackWithTwoQueuesTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		StackWithTwoQueues stack = new StackWithTwoQueues();
		
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		
		Assert.assertEquals("[1,2,3,4]", stack.toString());
		Assert.assertEquals(4L, stack.size());

		stack.pop();
		stack.pop();
		Assert.assertEquals("[1,2]", stack.toString());
		Assert.assertEquals(2L, stack.size());
		
		int item = stack.peek();
		Assert.assertEquals("[1,2]", stack.toString());
		Assert.assertEquals(2L, stack.size());
		Assert.assertEquals(2L, item);
		
		stack.pop();
		stack.pop();
		Assert.assertEquals("[]", stack.toString());
	}
}
