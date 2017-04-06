package com.coding.basic;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestArrayList {

	private List list = null;
	
	@Before
	public void init() {
		list = new ArrayList();
		
		list.add(0);
		list.add(1);
		list.add(3);
	}
	
	@Test
	public void addTest () {
		list.add(2,2);
//		System.out.println(list.toString());
		Assert.assertEquals("[0, 1, 2, 3]", list.toString());		
	}
	
	@Test
	public void getTest () {
		Assert.assertEquals(3, list.get(2));
	}
	
	@Test
	public void removeTest () {
		list.remove(0);
//		System.out.println(list.toString());
		Assert.assertEquals("[1, 3]", list.toString());
	}
	
	@Test
	public void sizeTest () {
		Assert.assertEquals(3, list.size());
	}
	
	@Test
	public void iteratorTest () {
		Object[] obj = new Object[list.size()];
		Iterator it = list.iterator();
		int i = 0;
		while (it.hasNext()) {
			obj[i] = it.next();
			i ++;
		}
//		System.out.println(Arrays.toString(obj));
		Assert.assertEquals(Arrays.toString(obj), list.toString());
	}

}
