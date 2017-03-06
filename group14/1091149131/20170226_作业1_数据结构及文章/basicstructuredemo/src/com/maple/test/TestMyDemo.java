package com.maple.test;

import com.maple.basic.ArrayList;
import com.maple.basic.BinaryTree;
import com.maple.basic.BinaryTreeNode;
import com.maple.basic.Iterator;
import com.maple.basic.LinkedList;
import com.maple.basic.Queue;
import com.maple.basic.Stack;

/**
 * 测试自己写的数据结构
 * @author group 14, QQ:1091149131
 */
public class TestMyDemo {
	public static void main(String[] args) {
		//BinaryTree
		BinaryTree<Integer> tree=new BinaryTree<>();
		tree.insert(3);
		tree.insert(2);
		tree.insert(5);
		//tree.insert(5);//error,duplicate
		tree.insert(1);
		tree.traversal(tree.getRoot());
		
		
		//Queue
		/*Queue queue=new Queue();
		queue.enQueue(0);
		queue.enQueue(1);
		
		System.out.println(queue.deQueue());*/
		
		//Stack
		/*Stack stack=new Stack();
		stack.push(0);
		stack.push(1);
		stack.push(2);
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.peek());*/
		
		//LinkedList
		/*LinkedList list2=new LinkedList();
		list2.add(0);
		list2.add(1);
		list2.addFirst(-1);
		list2.addLast(-2);
		
		list2.removeFirst();
		list2.removeLast();
		list2.remove(0);
		
		Iterator ite2=list2.iterator();
		while(ite2.hasNext()){
			System.out.println(ite2.next());
		}*/
		
		
		//ArrayList
		/*ArrayList list1=new ArrayList();
		list1.add(0);
		list1.add(1);
		//list1.add(3, -1);//error
		//list1.remove(2);//error
		Iterator ite=list1.iterator();
		while(ite.hasNext()){
			System.out.println(ite.next());
		}*/
		
		
		
		
	}
}
