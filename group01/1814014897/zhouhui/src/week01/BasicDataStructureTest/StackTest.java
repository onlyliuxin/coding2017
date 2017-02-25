package week01.BasicDataStructureTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week01.BasicDataStructure.Stack;


public class StackTest {
	
	Stack stack = new Stack();

	@Before
	public void setUp() throws Exception {
		for(int i =0 ;i <100;i++){
			stack.push(i);
		}
	}

	@Test
	public void testPush() {
		Assert.assertEquals(stack.peek(), 99);
		for(int i =0;i <200;i++){
			stack.push(i);
		}
		Assert.assertEquals(stack.peek(), 199);
		Assert.assertEquals(stack.size(), 300);
	}

	@Test
	public void testPop() {
		Assert.assertEquals(stack.pop(), 99);
		Assert.assertEquals(stack.pop(), 98);
		for(int i=0;i<98;i++){
			stack.pop();
		}
		Assert.assertEquals(stack.size(), 0);
	}

	@Test
	public void testPeek() {
		for(int i=0;i<100;i++){
			Assert.assertEquals(stack.peek(), 99);
			Assert.assertEquals(stack.size(), 100);
		}
		stack.pop();
		Assert.assertEquals(stack.peek(), 98);
		Assert.assertEquals(stack.peek(), 98);
	}

	@Test
	public void testIsEmpty() {
		Assert.assertEquals(stack.isEmpty(), false);
		for(int i =0 ;i <100;i++){
			stack.pop();
		}
		Assert.assertEquals(stack.isEmpty(), true);
	}

	@Test
	public void testSize() {
		stack.push(100);
		Assert.assertEquals(stack.size(), 101);
		stack.pop();
		Assert.assertEquals(stack.size(), 100);
		stack.peek();
		Assert.assertEquals(stack.size(), 100);
	}

}
