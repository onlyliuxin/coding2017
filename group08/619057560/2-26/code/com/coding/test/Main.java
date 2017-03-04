package com.coding.test;

import com.coding.basic.ArrayList;
import com.coding.basic.BinaryTreeNode;
import com.coding.basic.Iterator;
import com.coding.basic.LinkedList;
import com.coding.basic.Queue;
import com.coding.basic.Stack;

public class Main {
	
	private static void printArrayList(ArrayList list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		Iterator itr = list.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
	
	private static void printLinkedList(LinkedList list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	private static void printBinaryTree(BinaryTreeNode node) {
		if (node == null)
			return;
		
		printBinaryTree(node.getLeft());
		if (node.getData() != null)
			System.out.println(node.getData());
		printBinaryTree(node.getRight());
	}
	
	private static void testArrayList() {
		ArrayList arrayList = new ArrayList();
		arrayList.add(new Integer(1));
		arrayList.add(new Integer(2));
		arrayList.add(new Integer(3));
		arrayList.remove(2);
		arrayList.add(new Integer(4));
		arrayList.add(0,new Integer(5));
		printArrayList(arrayList);
	}
	
	private static void testLinkedList() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(new Integer(1));
		linkedList.add(new Integer(2));
		linkedList.add(new Integer(3));
		linkedList.remove(2);
		linkedList.add(new Integer(4));
		linkedList.add(0,new Integer(5));
		linkedList.removeFirst();
		printLinkedList(linkedList);
	}
	
	private static void testStack() {
		Stack stack = new Stack();
		stack.push(new Integer(1));
		stack.push(new Integer(2));
		stack.push(new Integer(3));
		System.out.println(stack.peek());
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		stack.push(new Integer(4));
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		//System.out.println(stack.pop());
	}
	
	private static void testQueue() {
		Queue queue = new Queue();
		queue.enQueue(new Integer(1));
		queue.enQueue(new Integer(2));
		queue.enQueue(new Integer(3));
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		//System.out.println(queue.deQueue());
	}
	
	private static void testBinaryTree() {
		BinaryTreeNode root = new BinaryTreeNode();
		root.insert(new Integer(4));
		root.insert(new Integer(3));
		root.insert(new Integer(5));
		root.insert(new Integer(1));
		root.insert(new Integer(8));
		root.insert(new Integer(2));
		root.insert(new Integer(7));
		root.insert(new Integer(6));
		printBinaryTree(root);
	}
	
	public static void main(String[] args) {
		testBinaryTree();
	}
}
