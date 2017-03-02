package com.coding;

import com.coding.basic.*;
public class Main {
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		
		list.add(0, "2xxx");
		list.add(1, "we");
		list.add(2, "sss");
		list.add("xing");
		list.remove(2);
		System.out.println(list.get(2));
		Iterator iterator = list.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println(list.size());

		LinkedList llist = new LinkedList();
		llist.add("hu");
		llist.add("zhao");
		llist.add(2,"xing");
		llist.addFirst("身骑白马");
		llist.addLast("德州小老虎");
		llist.add(5, "sf");
		llist.remove(5);
		llist.removeFirst();
		llist.removeLast();
		for (int i = 2; i >=0; i--) 
			System.out.print(llist.get(i));
		System.out.println(llist.size());

		Iterator literator = llist.iterator();
		while(literator.hasNext()) {
			System.out.println(literator.next());
		}
		
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		System.out.println(stack.peek());
		while(!stack.isEmpty())	
		System.out.println(stack.pop());

		Queue queue = new Queue();
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		System.out.println(queue.size());
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}
		
	}
}
