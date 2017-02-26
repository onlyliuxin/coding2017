package com.coding1094051862.basic;

import org.junit.Assert;
import org.junit.Test;

public class ArrayListTest {

	@Test
	public void test() {
		ArrayList list = new ArrayList();
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
	}

}
