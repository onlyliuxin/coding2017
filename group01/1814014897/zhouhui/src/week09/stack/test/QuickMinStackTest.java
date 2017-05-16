package week09.stack.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week09.stack.QuickMinStack;

public class QuickMinStackTest {

	QuickMinStack qms = new QuickMinStack();

	@Before
	public void setUp() throws Exception {
		qms.push(9);
		qms.push(5);
		qms.push(6);
		qms.push(10);
		qms.push(2);
		qms.push(3);
		qms.push(13);
	}

	@Test
	public void testPush() {
		for (int i = 0; i < 10; i++) {
			qms.push(i);
		}
		for (int i = 9; i >= 0; i--) {
			Assert.assertEquals(i, qms.pop());
		}
	}

	@Test
	public void testPop() {
		Assert.assertEquals(13, qms.pop());
		Assert.assertEquals(3, qms.pop());
		Assert.assertEquals(2, qms.pop());
		Assert.assertEquals(10, qms.pop());
		Assert.assertEquals(6, qms.pop());
		Assert.assertEquals(5, qms.pop());
		Assert.assertEquals(9, qms.pop());

	}

	@Test
	public void testFindMin() {
		Assert.assertEquals(2, qms.findMin());
		qms.push(2);
		qms.push(6);
		qms.push(26);
		Assert.assertEquals(2, qms.findMin());
		qms.pop();
		qms.pop();
		qms.pop();
		qms.pop();
		qms.pop();
		qms.pop();
		Assert.assertEquals(5, qms.findMin());

	}

}
