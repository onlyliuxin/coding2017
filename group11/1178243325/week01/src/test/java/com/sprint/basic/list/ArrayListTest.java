package com.sprint.basic.list;

import com.sprint.basic.Iterator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {
	private List list;
	
	@Before
	public void init() {
		list = new ArrayList();
	}
			
	@Test
	public void add() {
		for (int i = 0; i < 5; i++) {
			list.add(i);
		}
		/*Assert.assertTrue(args): if (args != true) to failed*/
		System.out.println(list);
		Assert.assertTrue(list.add(5));
		Assert.assertEquals(6, list.size());
		Assert.assertTrue(list.add(3, 10));
		Assert.assertEquals(7, list.size());
	
	} 

	@Test
	public void remove() {
		add();
		Assert.assertEquals(5, list.remove(6));
		Assert.assertEquals(6, list.size());
	}

	@Test
	public void get() {
		add();
		Assert.assertEquals(5, list.get(6));
	}
	
	@Test
	public void testIterator() {
		for (int i = 0; i < 10; i++) {
			Assert.assertTrue(list.add(i));
		}
		Iterator iter = list.iterator();
		int count = 0;
		while(iter.hasNext()) {
			Assert.assertEquals(count, iter.next());	
			count++;
		}
	}

}
