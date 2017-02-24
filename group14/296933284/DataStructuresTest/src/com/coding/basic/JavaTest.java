package com.coding.basic;

import org.junit.Test;

public class JavaTest {
	
	@Test
	public void binaryTreeNodeTest() {
//		BinaryTreeNode tree = new BinaryTreeNode(5);
//		
//		System.out.println(tree.getData());
	}
	
	
	@Test
	public void queueTest() {
		Queue queue = new Queue();
		
		// enQueue()
		for (int i = 0; i < 10; i++) {
			queue.enQueue("hello: " + i);
		}
		
		// size()
		System.out.println(queue.size()); // 10
		// isEmpty
		System.out.println(queue.isEmpty());
		
		// deQueue()
		for (int i = 0; i < 10; i++) {
			System.out.println(queue.deQueue());
		}
		
		// size()
		System.out.println(queue.size()); // 0
		
		// isEmpty
		System.out.println(queue.isEmpty());
	}
	
	
	@Test
	public void stackTest() {
		Stack stack = new Stack();
		
		// push()
		for (int i = 0; i < 10; i++) {
			stack.push("hello: " + i);
		}
		
		// size()
		System.out.println(stack.size());

		// pop()
		for (int i = 0; i < 10; i++) {
			System.out.println(stack.pop());
		}

		// isEmpty()
		System.out.println(stack.isEmpty());
		System.out.println(stack.size());
		
		stack.push("hello world 1");
		stack.push("hello world 2");
		stack.push("hello world 3");
		stack.push("hello world 4");
		
		// peek()
		System.out.println(stack.peek());
		
		// isEmpty()
		System.out.println(stack.isEmpty());
	}
	
	@Test
	public void linkedListTest() {
		LinkedList linkedList = new LinkedList();
		
		// add(obj)
		for (int i = 0; i < 10; i++) {
			linkedList.add("hello: " + i);
		}
		
		// size()
		System.out.println(linkedList.size()); // 10
		
		// get(index)
		for (int i = 0; i < linkedList.size(); i++) {
			System.out.println("-->" + linkedList.get(i));
		}
		
//		System.out.println("---->" + linkedList.get(10)); // java.lang.NullPointerException
		
		// remove()
		System.out.println("---->" + linkedList.remove(9)); // 9 
		System.out.println("---->" + linkedList.remove(5)); // 5
		System.out.println(linkedList.size()); // 8
		
		for (int i = 0; i < linkedList.size(); i++) {
			System.out.println("-->" + linkedList.get(i));
		}
		
		// removeFirst()			
		System.out.println("---->" + linkedList.removeFirst()); // 0
		// removeLast
		System.out.println("---->" + linkedList.removeLast()); // 8
		
		for (int i = 0; i < linkedList.size(); i++) {
			System.out.println("-->" + linkedList.get(i));
		}
		
		// add(index, obj)
		linkedList.add(4, "^_^");
		for (int i = 0; i < linkedList.size(); i++) {
			System.out.println("&&&&" + linkedList.get(i));
		}
		// addFirst()
		linkedList.addFirst("***first***");
		// addLast()
		linkedList.addLast("---last---");
		
		System.out.println(linkedList.get(0)); // ***first***
		System.out.println(linkedList.get(linkedList.size() - 1)); // ---last---
		System.err.println(linkedList.size()); // 9
		
		for (int i = 0; i < linkedList.size(); i++) {
			System.out.println("&&&&" + linkedList.get(i));
		}
		
		System.out.println("-------------------------------");
		// iterator
		Iterator iter = linkedList.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		System.out.println("-------------------------------");
	}
	
	@Test
	public void arrayListTest() {
		ArrayList arrayList = new ArrayList();
		
		// add(obj)
		for (int i = 0; i < 10; i++) {
			arrayList.add("hello: " + i);
		}
		
		// get(index)
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println("-->" + arrayList.get(i));
		}
		
		// add(index, obj)
		arrayList.add(5, "Tennyson");
		
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println("++>" + arrayList.get(i));
		}
		
		// size()
		System.out.println("size: " + arrayList.size());
		System.out.println("index 5: " + arrayList.get(5));
		
		// remove()
		Object value = arrayList.remove(5);
		System.out.println("index 5: " + value);
		System.out.println("size: " + arrayList.size());
		System.out.println("index5: " + arrayList.get(5));
		
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println("index " + i + " : " + arrayList.get(i));
		}
		
		System.out.println("-------------------------------");
		// iterator
		Iterator iter = arrayList.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		System.out.println("-------------------------------");
		
	}
}
