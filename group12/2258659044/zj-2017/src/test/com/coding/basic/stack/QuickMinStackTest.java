package test.com.coding.basic.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.stack.QuickMinStack;

public class QuickMinStackTest {

	QuickMinStack<Integer> stack ;
	@Before
	public void setUp() throws Exception {
		
		stack = new QuickMinStack<>();
	}

	@Test
	public void testPush() {
		
		for (int i = 0; i < 15; i++) {
			stack.push(i);
		}
		Assert.assertEquals(14, (int)stack.pop());
	}

	@Test
	public void testPop() {
		
		for (int i = 0; i < 10; i++) {
			stack.push(i);
		}
		for (int i = 9; i >= 0; i--) {
			Assert.assertEquals(i, (int)stack.pop());
		}
	}

	@Test
	public void testFindMin() {
		
		for (int i = 5; i < 15; i++) {
			stack.push(i);
		}
		Assert.assertEquals(5, (int)stack.findMin());
		
		stack.push(5);
		stack.push(3);
		stack.push(4);
		
		stack.pop();
		Assert.assertEquals(3, (int)stack.findMin());
		
		stack.pop();
		Assert.assertEquals(5, (int)stack.findMin());
		
		stack.pop();
		Assert.assertEquals(5, (int)stack.findMin());
	}

}
