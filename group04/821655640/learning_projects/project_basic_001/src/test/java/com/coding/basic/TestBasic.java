package com.coding.basic;


import org.junit.Test;

public class TestBasic {
	
	@Test
	public void testBinaryTree() {
		BinaryTreeNode<Integer> bt = new BinaryTreeNode<Integer>();
		bt.insert(10);
		for (int i = 0; i < 5; i++) {
			bt.insert(i);
		}
		System.out.println(bt);
		
	}
	
	@Test
	public void testStack()  {
		Stack stack = new Stack();
		System.out.println(stack.isEmpty());
		for (int i = 0; i < 5; i++) {
			stack.push(i);
		}
		
		System.out.println(stack);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack);
		System.out.println(stack.peek());
		System.out.println(stack);
		System.out.println(stack.isEmpty());
		System.out.println(stack.size());
		
	}
	
	@Test
	public void testQueue() {
		 Queue queue = new Queue();
		 System.out.println(queue.isEmpty());
		 for (int i = 0; i < 5; i++) {
			queue.enQueue(i);
		}
		 System.out.println(queue.isEmpty());
		 System.out.println(queue);
		 queue.deQueue();
		 queue.deQueue();
		 System.out.println(queue);
		 System.out.println(queue.size());
		 
	}
	
	
	
	@Test
	public void testLinkedList() {
		LinkedList link = new LinkedList();
		for(int i=0;i<3;i++) {
			link.add(i);
		}
		System.out.println(link);
		link.add(123);
		link.addFirst("first");
		link.addLast("last");
		System.out.println(link);
		link.removeFirst();
		link.removeLast();
		System.out.println(link);
		//link.remove(12);
		System.out.println(link.get(2));
		
	}
	
	@Test
	public  void testArrayList(){
		ArrayList a = new ArrayList();
		for (int i = 0; i <130; i++) {
			a.add(i);
		}
		System.out.println(a);
		a.add(3,"abc");
		System.out.println(a);
		a.remove(0);
		System.out.println(a);
		System.out.println(a.get(2));
		a.add("tttttttt");
		System.out.println(a);
		
	}
	
}
