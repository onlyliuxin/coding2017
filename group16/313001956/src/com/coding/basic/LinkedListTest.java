package com.coding.basic;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListTest {

	@Test
	public final void testReverse() {
		
		LinkedList list=new LinkedList();
		list.add(3);
		list.add(7);
		list.add(10);
		
		LinkedList testlist=new LinkedList();
		testlist.add(10);		
		testlist.add(7);
		testlist.add(3);
		
		list.reverse(list);
		Assert.assertEquals(list.size(), testlist.size());
		Assert.assertEquals(list.get(0), testlist.get(0));
		Assert.assertEquals(list.get(1), testlist.get(1));
		Assert.assertEquals(list.get(2), testlist.get(2));
	}

}
