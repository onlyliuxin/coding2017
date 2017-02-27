package com.coding.basic.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.coding.basic.impl.LinkedList;

/**
 * LinkedList 简单测试
 * @author 240094626
 *
 */
public class LinkedListTest {

	@Test
	public void test() {
		LinkedList list = new LinkedList();
		
		System.out.println("******测试add(Object o ):添加第一个元素0******");
		list.add(0);
		System.out.println("LinkedList print:"+list.toString());
		System.out.println("******测试add(int index,Object o):添加第二个元素1******");
		list.add(1);
		System.out.println("******测试addLast(Object o):往链表最后添加元素3******");
		list.addLast(3);
		System.out.println("******测试addFirst(Object o):往链表最前面添加元素5******");
		list.addFirst(5);
		System.out.println("LinkedList print:"+list.toString());
		
		System.out.println("******测试remove(int index):删除第4个元素:index=3******");
		list.remove(3);
		System.out.println("LinkedList print:"+list.toString());
		
		System.out.println("******测试addFirst(int Object o):链表最前面添加素2******");
		list.addFirst(2);
		System.out.println("LinkedList print:"+list.toString());
		
		// 断言第一个元素为0
		assertEquals(2, list.get(0));
		
		list.addLast(3);
		list.addFirst(5);
		System.out.println("LinkedList print:"+list.toString());
		// 断言最后一个元素为3
		assertEquals(3,list.get(list.size()-1));
	}
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(LinkedListTest.class);
		for(Failure failure : result.getFailures()){
			System.out.println(failure.toString());
		}
		System.out.println("test success!:"+result.wasSuccessful());
	}

}
