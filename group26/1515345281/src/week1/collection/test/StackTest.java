package week1.collection.test;

import static org.junit.Assert.*;

import org.junit.Test;

import week1.collection.Stack;

public class StackTest {

	private Stack stack=new Stack();
	
	@Test
	public void testPush(){
		stack.push("hello");
		stack.push("world");
	    assertEquals("world",stack.peek());
	}
	
	@Test
	public void testPop(){
		stack.push("hello");
		stack.push("world");
	    assertEquals("world",stack.pop());
	    assertEquals(1,stack.size());
	}
	
	@Test
	public void testPeek(){
		stack.push("world");
	    assertEquals("world",stack.peek());
	    stack.pop();
	    try{
	    	stack.peek();
	    	fail("stack is empty,can't do peek");
	    	
	    }catch(Exception ex){
	    	
	    }
	}
	
	@Test
	public void testEmpty(){
		assertEquals(true,stack.isEmpty());
		stack.push("hello");
		stack.push("world");
	    assertEquals(false,stack.isEmpty());
	}
	
	@Test
	public void testSize(){
		stack.push("hello");
		stack.pop();
		assertEquals(0,stack.size());
	}
}
