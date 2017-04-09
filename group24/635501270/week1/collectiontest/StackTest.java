package week1.collectiontest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import week1.collections.Stack;

public class StackTest {
	Stack stack = null;
	@Before
	public void init(){
		stack = new Stack();
		for(int i=1;i<=10;i++){
			stack.push(i);
		}
	}
	
	@Test
	public void test1(){
		assertEquals(stack.peek(), 10);
		assertEquals(stack.peek(), 10);
		assertEquals(stack.peek(), 10);
		assertEquals(stack.pop(), 10);
		assertEquals(stack.pop(), 9);
		assertEquals(stack.size(), 8);
	}
	
	@Test
	public void test2(){
		for(int i=10;i>=1;i--){
			assertEquals(stack.pop(), i);
		}
		assertEquals(stack.isEmpty(), true);
	}
}
