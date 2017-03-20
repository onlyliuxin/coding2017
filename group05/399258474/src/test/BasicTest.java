package test;

import org.junit.Test;

import com.coding.basic.ArrayList;
import com.coding.basic.LinkedList;
import com.coding.basic.Queue;
import com.coding.basic.Stack;

public class BasicTest {

	@Test
	public void ArrayListTest() {
		ArrayList list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(list);
		list.add(1, 99);
		System.out.println(list);
		list.remove(1);
		System.out.println(list);
	}
	
	@Test
	public void LinkedListTest(){
		LinkedList l = new LinkedList();
		l.add(1);
		l.add(2);
		l.add(3);
		System.out.println(l);
		l.add(1, 99);
		System.out.println(l);
		l.remove(1);
		System.out.println(l);
		System.out.println(l.size());
	}
	
	@Test
	public void StackTest(){
		Stack s = new Stack();
		s.push(1);
		s.push(2);
		System.out.println(s);
		if(s.isEmpty()){
			System.out.println("空");
		}else{
			System.out.println("非空");
			
		}
		System.out.println(s.peek());
		s.pop();
		System.out.println(s);
		s.pop();
		System.out.println(s);
		if(s.isEmpty()){
			System.out.println("空");
		}else{
			System.out.println("非空");
			
		}
		s.pop();
	}
	
	@Test
	public void QueueTest(){
		Queue q = new Queue();
		q.enQueue(1);
		q.enQueue(2);
		System.out.println(q);
		q.deQueue();
		System.out.println(q);
		q.enQueue(3);
		System.out.println(q);
		System.out.println(q.size());
	}

}
