package com.coding.basic;

import org.junit.Assert;
import org.junit.Test;

public class TestForThey {

	@Test
	public void testArrayList(){
		ArrayList list=new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		list.add(11);
		list.add(12);
		list.add(13);
		Assert.assertEquals(13, list.size());
		list.add(2, 100);
		Assert.assertEquals(100, list.get(2));
		list.remove(3);
		Assert.assertEquals(4, list.get(3));
		int[] a=new int[]{1,2,100,4,5,6,7,8,9,10,11,12,13};
		Iterator it=list.iterator();
		int i=0;
		while(it.hasNext()){
			Assert.assertEquals(a[i], it.next());
			i++;
		}		
	}
	
	@Test
	public void testLinkedList(){
		
		LinkedList list=new LinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(2, 100);
		list.add(6,200);
		Assert.assertEquals(11, list.size());
		Assert.assertEquals(100, list.get(2));
		list.remove(2);
		Assert.assertEquals(3, list.get(2));
		list.addFirst(1111);		
		Assert.assertEquals(1111, list.get(0));
		list.addLast(9999);
		Assert.assertEquals(9999, list.get(list.size()-1));
		list.removeFirst();
		Assert.assertEquals(1, list.get(0));
		list.removeLast();
		Assert.assertEquals(9, list.get(list.size()-1));

		int[] a=new int[]{1,2,3,4,5,200,6,7,8,9};
		Iterator it=list.iterator();
		int i=0;
		while(it.hasNext()){
			Assert.assertEquals(a[i], it.next());
			i++;
		}		
	}
	
	@Test
	public void testQueue(){
		Queue queue=new Queue();
		Assert.assertEquals(true,queue.isEmpty());
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.enQueue(4);
		queue.enQueue(5);
		queue.enQueue(6);
		queue.enQueue(7);
		queue.enQueue(8);
		queue.enQueue(9);
		queue.enQueue(10);
		queue.enQueue(11);
		queue.enQueue(12);
		queue.enQueue(13);
		Assert.assertEquals(13,queue.size());
		Assert.assertEquals(1,queue.deQueue());		
	}
	
	@Test
	public void testStack(){
		Stack stack=new Stack();
		Assert.assertEquals(true,stack.isEmpty());
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(6);
		stack.push(7);		
		stack.push(8);
		stack.push(9);
		Assert.assertEquals(9,stack.size());
		Assert.assertEquals(9,stack.peek());
		Assert.assertEquals(9,stack.pop());
		Assert.assertEquals(8,stack.peek());
	}
}
