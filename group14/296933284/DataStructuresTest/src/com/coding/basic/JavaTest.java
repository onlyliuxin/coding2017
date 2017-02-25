package com.coding.basic;

import org.junit.Test;

public class JavaTest {

	@Test
	public void binarySearchTreeTest() {
		BinarySearchTree bSTree = new BinarySearchTree(5);

		System.out.println(bSTree.getData());

		// insert
		bSTree.insert(1);
		bSTree.insert(2);
		bSTree.insert(4);
		bSTree.insert(6);
		bSTree.insert(7);
		bSTree.insert(8);
		System.out.println("-----------------");
		// inOrder
		bSTree.inOrder(bSTree);

		System.out.println("-----------------");
		// levelOrder
		bSTree.levelOrder(bSTree);
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
		
		// add() addLast()
		for (int i = 0; i < 5; i++) {
			linkedList.add("hello: " + i);
		}
		
		// iterator() get() getPreNode()
		Iterator iter = linkedList.iterator();
		
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		System.out.println("-----------------------");
		LinkedList linkedList1 = new LinkedList();

		// addFirst()
		for (int i = 0; i < 5; i++) {
			linkedList1.addFirst("hello: " + i);
		}
		
		Iterator iter1 = linkedList1.iterator();
		
		while (iter1.hasNext()) {
			System.out.println(iter1.next());
		}
		
		System.out.println("-----------------------");
		// remove()
		System.out.println(linkedList1.remove(0)); // hello: 4
		
		System.out.println("-----------------------");
		// removeFirst()  removeLast()
		System.out.println(linkedList1.removeFirst()); // hello: 3
		System.out.println(linkedList1.removeLast()); // hello: 0
		
		System.out.println("-----------------------");
		// size()
		System.out.println(linkedList.size()); // 5
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
