package com.coding.basic.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.coding.basic.Iterator;
import com.coding.basic.impl.Stack;

/**
 * stack 简单测试
 * @author 240094626
 *
 */
public class StackTest {

	@Test
	public void test() {
		Stack stack = new Stack();
		System.out.println("******测试push(Object o):压入第一个元素0******");
		stack.push(0);
		System.out.println("Stack print:"+stack.toString());
		
		System.out.println("******测试push(Object o):压入第二个元素2******");
		stack.push(2);
		System.out.println("Stack print:"+stack.toString());
		
		System.out.println("******测试peek():从栈尾取出2，不删除******");
		stack.peek();
		System.out.println("Stack print:"+stack.toString());
		
		System.out.println("******测试peek():再次从栈尾取出2，不删除******");
		// 断言出栈为2
		assertEquals(2,stack.peek());
		// 断言size为2
		assertEquals(2,stack.size());
		
		System.out.println("******测试pop():末尾元素2出栈,并移除******");
		// 断言出栈为2
		assertEquals(2,stack.pop());
		System.out.println("Stack print:"+stack.toString());
		
		// 断言不为空
		assertEquals(false,stack.isEmpty());
		// 断言size为1
		assertEquals(1,stack.size());
		// 添加3，5 两个元素
		stack.push(3);
		stack.push(5);
		System.out.println("Stack print:"+stack.toString());
		// 测试迭代器
		Iterator it = stack.iterator();
		int i = 1;
		while(it.hasNext()){
			System.out.println("第"+i+"个元素："+it.next());
			i++;
		}
		
	}
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(StackTest.class);
		for(Failure failure : result.getFailures()){
			System.out.println(failure.toString());
		}
		System.out.println("test success!:"+result.wasSuccessful());
	}

}
