package com.m0226.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.junit.Test;


/**
 * 测试Java中的API
 * @author group 14, QQ:1091149131
 */
public class TestAPIDemo {
	public static void main(String[] args) {
		
		//Stack
		/*Stack stack=new Stack();
		stack.push(0);
		stack.push(1);
		stack.push(2);
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.peek());*/
		
		
		
		
		
	}
	@Test
	public void testLinkedList() {
		//LinkedList
		LinkedList<Integer> list2=new LinkedList<>();
		list2.add(0);
		list2.add(1);
		list2.add(2);
		list2.add(2);
		System.out.println(list2.indexOf(2));
		
//		list2.addLast(3);
//		list2.remove(0);
		//list2.removeFirst();
		/*Iterator ite2=list2.iterator();
		while(ite2.hasNext()){
			System.out.println(ite2.next());
		}*/
	}
	@Test
	public void testArrayList() {
		//ArrayList
		ArrayList list1=new ArrayList();
		list1.contains(3);
		list1.add(0);
		list1.add(1);
		//list1.add(3, -1);//error
		//list1.remove(2);//error
		Iterator ite=list1.iterator();
		while(ite.hasNext()){
			System.out.println(ite.next());
		}
		fail("Not yet implemented");
	}
	
}
