package com.coding.basic;

import org.junit.Assert;
import org.junit.Test;

public class ArrayListTest {

	@Test
	public void test() {
		List list = new ArrayList();
		for(int i = 0; i < 10; i++) {
			list.add(i);
		}
		Assert.assertEquals(10, list.size());
		list.add(11);
		list.add(3,99);
		Assert.assertEquals(99, list.get(3));
		Assert.assertEquals(12, list.size());
		Assert.assertEquals(99, list.remove(3));
		Assert.assertEquals(11, list.size());
		Iterator iterator = list.iterator();
		for (int i = 0; i< list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("======");
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
}
