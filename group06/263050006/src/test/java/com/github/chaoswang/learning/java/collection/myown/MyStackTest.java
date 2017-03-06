package com.github.chaoswang.learning.java.collection.myown;

import java.util.EmptyStackException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MyStackTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testPushAndPop(){
		MyStack<String> myStack = new MyStack<String>(3);
		myStack.push("1");
		myStack.push("2");
		myStack.push("3");
		Assert.assertEquals("3", myStack.pop());
		Assert.assertEquals("2", myStack.peek());
		Assert.assertEquals("2", myStack.pop());
		Assert.assertEquals("1", myStack.peek());
	}
	
	@Test
	public void testPopWhenQueueIsEmpty(){
		thrown.expect(EmptyStackException.class);
		MyStack<String> myStack = new MyStack<String>(3);
		myStack.push("1");
		myStack.pop();
		//¼ÌÐøÒÆ³ýÅ×Òì³£
		myStack.pop();
	}
	
	@Test
	public void testSearch(){
		MyStack<String> myStack = new MyStack<String>(3);
		myStack.push("1");
		myStack.push("2");
		myStack.push("3");
		Assert.assertEquals(2, myStack.search("1"));
		Assert.assertEquals(1, myStack.search("2"));
		Assert.assertEquals(0, myStack.search("3"));
	}
}
