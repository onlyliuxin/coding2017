package com.coding.basic.stack;

import static org.junit.Assert.*;
import java.util.Stack;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class StackUtilTest {
	private static Stack<Integer> testStack;
	@Before
	public void setup() throws Exception {
		System.out.println("初始化堆栈:");
		testStack = new Stack<Integer>();
		testStack.push(1);
		testStack.push(2);
		testStack.push(3);
		testStack.push(4);
		testStack.push(5);
		StackUtil util = new StackUtil();
		util.printStack(testStack);
	}

	@Test
	public void testReverse() {
		System.out.println("测试Stack反转:");
		StackUtil util = new StackUtil();
		util.reverse(testStack);	
		Assert.assertEquals("5,4,3,2,1", util.printStack(testStack));
	}

	@Test
	public void testRemove() {
		System.out.println("测试Stack删除:");
		StackUtil util = new StackUtil();
		util.remove(testStack, 8);
		Assert.assertEquals("1,2,3,4,5",util.printStack(testStack));
		util.remove(testStack, 2);
		Assert.assertEquals("1,3,4,5",util.printStack(testStack));
		
	}

	@Test
	public void testGetTop() {
		System.out.println("测试获取Top len数据:");
		StackUtil util = new StackUtil();
		Object[] objArray1 = util.getTop(testStack, 9);
		Assert.assertEquals(null, objArray1);
		Assert.assertEquals("1,2,3,4,5", util.printStack(testStack));
		Object[] objArray2 = util.getTop(testStack, 3);
		String actualStr = "";
		for(int i=0;i<objArray2.length;i++){
			actualStr = actualStr + objArray2[i].toString();
			if(i!=objArray2.length-1){
				actualStr = actualStr + ",";
			}
		}
		Assert.assertEquals("5,4,3", actualStr);
		Assert.assertEquals("1,2,3,4,5", util.printStack(testStack));
	}

	@Test
	public void testIsValidPairs() {
		String testStr1 = "([e{d}f])"; 
		String testStr2 = "{ab,[e(d)f13{g45}h89*]}";
		String testStr3 = "([b{x]y})";
		Assert.assertTrue(StackUtil.isValidPairs(testStr1));
		Assert.assertTrue(StackUtil.isValidPairs(testStr2));
		Assert.assertFalse(StackUtil.isValidPairs(testStr3));
	}

	@Test
	public void testPrintStack() {
		System.out.println("测试Stack打印:");
		StackUtil sta_util = new StackUtil();
		sta_util.printStack(testStack);
	}

}
