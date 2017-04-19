package test;

import org.junit.Assert;
import org.junit.Test;

import basic.Iterator;
import basic.LinkedList;

public class LinkedListTest {
	
	@Test
	public void test01(){
		LinkedList linkedList = new LinkedList();
		linkedList.add(122);
		linkedList.add("qwe");
		linkedList.add(133);
		iterator(linkedList);
		
		linkedList.add(1, "asd");
		iterator(linkedList);
		
		linkedList.addFirst("1");
		iterator(linkedList);
		
		linkedList.addLast("zxc");
		iterator(linkedList);
		
		Object remove = linkedList.remove(2);
		Assert.assertEquals("asd", remove);
		
		Object removeFirst = linkedList.removeFirst();
		Assert.assertEquals("1", removeFirst);
		
		Object removeLast = linkedList.removeLast();
		Assert.assertEquals("zxc", removeLast);
		
		int size = linkedList.size();
		Assert.assertEquals(3, size);
		
		
		
	}
	
	public static void iterator(LinkedList linkedList){
		Iterator iterator = linkedList.iterator();
		while(iterator.hasNext()){
			System.out.print(iterator.next()+"   ");
		}
		System.out.println();
	}

}
