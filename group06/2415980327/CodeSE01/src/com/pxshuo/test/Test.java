package com.pxshuo.test;


import com.pxshuo.basic.Iterator;
import com.pxshuo.basic.TreeData;
import com.pxshuo.basic.impl.ArrayList;
import com.pxshuo.basic.impl.BinaryTree;
import com.pxshuo.basic.impl.LinkedList;
import com.pxshuo.basic.impl.Queue;
import com.pxshuo.basic.impl.Stack;

public class Test {
	public static void main(String[] args) {
//		LinkedList arrayList = new LinkedList();
//		arrayList.add("hello1");
//		arrayList.add("hello2");
//		arrayList.add(9,"hello3");
//		//arrayList.add(10,"hello4");
//		arrayList.addLast("hi");
//		arrayList.addLast("hihi");
//		arrayList.addFirst("hi1");
//		arrayList.removeFirst();
//		arrayList.removeLast();
//		arrayList.add(1,"hi1");
//		arrayList.remove(1);
//		//arrayList.add(0, "hi");
//		//arrayList.remove(8);
//		//arrayList.remove(0);
//		for (Iterator iterator = arrayList.iterator(); iterator.hasNext();) {
//			System.out.println("hi"+iterator.next());
//		}
		//Queue queue = new Queue();
//		Stack stack = new Stack();
//
//		for (int i = 0; i < 10; i++) {
//			//queue.enQueue("test-" + i);
//			stack.push("test-" + i);
//		}
//		for(int i =0; i< 11; i++)
//		{
//			System.out.println(stack.pop());
//		}
//		stack.push("test-" + 233);
//		System.out.println(stack.pop());
		
		BinaryTree binaryTree = new BinaryTree();
		binaryTree.add(new TreeData(5));
		binaryTree.add(new TreeData(2));
		binaryTree.add(new TreeData(7));
		binaryTree.add(new TreeData(1));
		binaryTree.add(new TreeData(6));
		binaryTree.add(new TreeData(4));
		binaryTree.add(new TreeData(8));
		
		System.out.println(binaryTree.get(5).getClass());
		
		//binaryTree.display();
	}
}
