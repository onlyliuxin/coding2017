package com.coding.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.coding.basic.ArrayList;
import com.coding.basic.BinaryTreeNode;
import com.coding.basic.Iterator;
import com.coding.basic.LinkedList;
import com.coding.basic.List;
import com.coding.basic.Queue;
import com.coding.basic.Stack;



public class JavaTest {
	/**
	 * List集合单元测试内容
	 */
	@Test
	public void test() {
		List list =  new ArrayList();
		list.add(0, "aa");
	}
	
	@Test
	public void test1() {
		List list =  new ArrayList();
		list.add("aa");
		System.out.println(list.get(0));
	}
	
	@Test
	public void test2() {
		List list =  new ArrayList();
		list.add("aa");
		System.out.println(list.get(0));
		list.remove(0);
		System.out.println(list.get(0));
	}
	
	@Test
	public void test3() {
		List list =  new ArrayList();
		list.add("aa");
		System.out.println(list.size());
		list.add("aa");
		System.out.println(list.size());
	}
	
	@Test
	public void test4() {
		ArrayList list =  new ArrayList();
		Integer [] str={11,22};
		list.add(str);
		Iterator it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
			
		}
		LinkedList ll = new LinkedList();
	}
	
	/**
	 * LinkedList测试
	 */
	@Test
	public void test5() {
		List list =  new LinkedList();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		System.out.println(list.get(1));
	}
	
	@Test
	public void test6() {
		List list =  new LinkedList();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		list.add(2, "ff");
		System.out.println(list.get(3));
	}
	
	@Test
	public void test7() {
		List list =  new LinkedList();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		list.remove(2);
		System.out.println(list.get(0)+"----"+list.get(1));
	}
	
	@Test
	public void test8() {
		LinkedList list =  new LinkedList();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		System.out.println(list.get(0));
		list.addFirst("haha");
		System.out.println(list.get(0));
	}
	
	@Test
	public void test9() {
		LinkedList list =  new LinkedList();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		list.addLast("haha");
		System.out.println(list.get(3));
	}
	
	@Test
	public void test10() {
		LinkedList list =  new LinkedList();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		list.removeFirst();
		System.out.println(list.get(0));
	}
	
	@Test
	public void test11() {
		LinkedList list =  new LinkedList();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		list.removeLast();
		System.out.println(list.get(2));
	}
	
	@Test
	public void test12() {
		LinkedList list =  new LinkedList();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		Iterator it = list.iterator();
		while(it!=null&&it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	/**
	 * Queue集合测试
	 */
	@Test
	public void test13() {
		Queue q = new Queue();
		System.out.println(q.size()+"------"+q.isEmpty());
		q.enQueue("aaa");
		q.enQueue("bbb");
		q.enQueue("ccc");
		System.out.println(q.size()+"------"+q.isEmpty());
		q.deQueue();
		System.out.println(q.size()+"------"+q.isEmpty());
	}
	
	/**
	 * Stack集合测试
	 */
	@Test
	public void test14() {
		Stack q = new Stack();
		System.out.println(q.isEmpty());
		System.out.println(q.size());
		q.push("aa");
		q.push("bb");
		q.push("cc");
		q.push("dd");
		System.out.println(q.size());
		q.pop();
		System.out.println(q.size());
		System.out.println(q.peek());
		q.pop();
		System.out.println(q.size());
		System.out.println(q.peek());
		System.out.println(q.isEmpty());
	}
	
	/**
	 * BinaryTree测试
	 */
	@Test
	public void test15(){
		BinaryTreeNode bt = new BinaryTreeNode();
		Integer[] data={3,2,5,4,6,8};  
		for(Integer i=0;i<data.length;i++){  
		   bt.insert(data[i]);  
		}  
		System.out.println(bt.getData());
		
	}
	
	
	@Test
	public void test16(){
		int x = 11;
		int y = 33;
		int min = Math.min(x, y);//两个值中较小的值
		int maxValue = 0;//最大公约数
		//倒序遍历求当第一次出现时就为两个数的最大公约数
		for(int i=min;i>=1;i--){
			if(y%i==0&&x%i==0){
				maxValue = i;
				break;
			}
		}
		System.out.println(x/maxValue+"比"+y/maxValue);
		
	}
	
	

}
