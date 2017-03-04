package com.coding.basic;
import com.coding.basic.*;

class Main{

	public static void  main(String[] args){
		System.out.println("数组测试开始");
		ArrayListTest();
		System.out.println("----------------分割线----------------");
		System.out.println("链表测试开始");
		LinkedListTest();	
		System.out.println("----------------分割线----------------");
		System.out.println("栈测试开始");
		StatckTest();
		System.out.println("----------------分割线----------------");
		System.out.println("队测试开始");
		QueueTest();
	}
	
	public static void ArrayListTest(){
		ArrayList list = new ArrayList(2);
		list.add("HelloBobi0");
		list.add("HelloBobi1");
		list.add("HelloBobi2");
		list.add("HelloBobi3");
		list.add("HelloBobi4");
		list.add("HelloBobi5");
		System.out.println((String)list.get(0));
		System.out.println((String)list.get(4));
		list.add(3, "Hei Man");
		list.remove(5);
		System.out.println(list);
		System.out.println("size:=" + list.size());
	}
	
	public static void LinkedListTest(){
		LinkedList ll = new LinkedList();
		
		
		ll.add("SingleDog0");
		ll.add("SingleDog1");
		ll.add("SingleDog2");
		ll.add("SingleDog3");
		ll.add("SingleDog4");
		ll.add("SingleDog5");
		
		System.out.println((String)(ll.get(1)));
		System.out.println(ll);
		System.out.println("size:=" + ll.size());
		ll.remove(0);
		ll.removeFirst();
		ll.removeLast();
		System.out.println(ll);		
	}
	
	public static void StatckTest(){
		Stack stack = new Stack();
		stack.push("虾师傅0");
		stack.push("虾师傅1");
		stack.push("虾师傅2");
		stack.push("虾师傅3");
		stack.push("虾师傅4");
		
		stack.pop();
		System.out.println(stack.peek());
		System.out.println(stack);
		
	}
	public static void QueueTest(){
		Queue queue = new Queue();
		queue.enQueue("龙师傅0");
		queue.enQueue("龙师傅1");
		queue.enQueue("龙师傅2");
		queue.enQueue("龙师傅3");
		queue.enQueue("龙师傅4");
		
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.size());
	
		
	}
}