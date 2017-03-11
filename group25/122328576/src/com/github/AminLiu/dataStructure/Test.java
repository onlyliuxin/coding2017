package com.github.AminLiu.dataStructure;

public class Test {
	public static void main(String[] args) {
		testArrayList();
		testLinkedList();
		testQueue();
		testStack();
	}

	public static void testArrayList() {
		ArrayList arrayList = new ArrayList();
		arrayList.add("abc");
		arrayList.add("ww");
		arrayList.add("eee");
		System.out.println(arrayList.size());
		System.out.println(arrayList.toString());
		arrayList.add(2, "sdsaddad");
		System.out.println(arrayList.size());
		System.out.println(arrayList.toString());
		arrayList.remove(2);
		System.out.println(arrayList.size());
		System.out.println(arrayList.toString());
		System.out.println(arrayList.size());
		System.out.println(arrayList.get(2));
		Iterator iterator = arrayList.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		System.out.println("--------------------------");
	}

	public static void testLinkedList() {
		LinkedList linkedList = new LinkedList();
		linkedList.add("123");
		linkedList.add("456");
		linkedList.add("789");
		System.out.println(linkedList.toString());
		linkedList.remove(0);
		System.out.println(linkedList.toString());
		linkedList.add(0, "asd");
		System.out.println(linkedList.toString());
		linkedList.removeLast();
		System.out.println(linkedList.toString());
		System.out.println(linkedList.size());
		linkedList.add(2, "sdsad");
		System.out.println(linkedList.toString());
		linkedList.addLast("sdad");
		linkedList.addFirst("111111");
		System.out.println(linkedList.toString());
		Iterator iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		// Object sss = linkedList.get(5);
		// System.out.println(sss.toString());
		linkedList.removeFirst();
		System.out.println(linkedList.toString());
	}

	public static void testQueue() {
		Queue queue = new Queue();
		queue.enQueue("sd");
		queue.deQueue();
		System.out.println(queue.size());
		System.out.println(queue.toString());
	}

	public static void testStack() {
		Stack stack = new Stack();
		stack.push("123");
		System.out.println(stack.peek());
		System.out.println(stack.toString());
		stack.push("123");
		stack.push("34");
		System.out.println(stack.size());
		stack.pop();
		System.out.println(stack.toString());
	}
}
