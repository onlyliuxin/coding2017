package com.oneflyingleaf.util;

import org.junit.Assert;
import org.junit.Test;

public class TestClass {

	@Test
	public void testArrayList(){
		ArrayList list = new ArrayList();
		
		list.add("1A");
		list.add("2B");
		list.add("3B");
		
		list.add(3, "4D");
		
		list.remove(3);
		Assert.assertEquals(3, list.size());
		list.remove(0);
		Assert.assertEquals(2, list.size());
		Iterator iterator = list.iterator();
		
		String []str = {"2B","3B"};
		int i = 0;
		
		while(iterator.hasNext()){
			Assert.assertEquals(str[i++], (String)iterator.next());
		}
		
	}
	
	@Test
	public void testLinkedList(){
		LinkedList list = new LinkedList();
		String []str = {"1A","2B","3C","4D","5E","6F"};
		
		for(String s : str){
			list.add(s);
		}
		
		for(int i = 0; i<list.size();i++){
			Assert.assertEquals(str[i], list.get(i));
		}
		
		Iterator iterator = list.iterator();
		int i = 0;
		
		while(iterator.hasNext()){
			Assert.assertEquals(str[i++], (String)iterator.next());
		}
		
		
		list.addFirst("0Q");
		Assert.assertEquals("0Q", list.get(0));
		list.removeFirst();
		Assert.assertEquals("1A",list.get(0));
		
		
		Assert.assertEquals(str.length, list.size());
		
		
		Assert.assertEquals(str[3],list.remove(3));
		
		Assert.assertEquals(str[4], list.get(3));
		
		list.reverse();
		list.reverse();
		
		for(int j = 0; j<list.size();j++){
			System.out.println(list.get(j));
		}
		
		
		
	}
	
	@Test
	public void testQueue(){
		Queue queue = new Queue();
		
		String []str = {"1A","2B","3C","4D","5E","6F"};
		
		for(String s : str){
			queue.enQueue(s);
		}
		
		int size = queue.size();
		for(int i = 0;i < size;i++){
			Assert.assertEquals(str[i],queue.deQueue());
		}
		
		Assert.assertEquals(0, queue.size());
	}
	
	@Test
	public void testStack(){
		Stack stack = new Stack();
		String []str = {"1A","2B","3C","4D","5E","6F"};

		for(String s : str){
			stack.push(s);
		}
		
		int size = stack.size();
		
		for(int i = 0;i < size;i++){
			Assert.assertEquals(str[size - 1 - i],stack.pop());
		}
		
	}
	
	
	
}
