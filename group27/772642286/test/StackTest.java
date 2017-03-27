package week01.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week01.basic.Stack;

public class StackTest {

	private Stack stack;
	
	@Before
	public void init(){
		stack = new Stack();
		for(int i=1;i<=500;i++){
			stack.push(i);
  		}
	}
	
	@Test
	public void pushTest(){
		Assert.assertEquals(500, stack.size());
	}
	
	@Test
	public void popTest(){
		for(int i=1;i<=500 ;i++){
			Assert.assertEquals(i, stack.pop());
		}
	}
	
	@Test
	public void peekTest(){
		Assert.assertEquals(1, stack.peek());
		Assert.assertEquals(1, stack.peek());
		Assert.assertEquals(1, stack.pop());
		Assert.assertEquals(2, stack.peek());
		Assert.assertEquals(2, stack.peek());
	}
	
	@Test
	public void isEmpty(){
		Assert.assertEquals(false, stack.isEmpty());
		for(int i=1;i<=500 ;i++){
			Assert.assertEquals(i, stack.pop());
		}
		Assert.assertEquals(true, stack.isEmpty());
	}
	
	@Test
	public void size(){
		for(int i=499;i>0 ;i--){
			stack.pop();
			Assert.assertEquals(i, stack.size());
		}
	}
}
