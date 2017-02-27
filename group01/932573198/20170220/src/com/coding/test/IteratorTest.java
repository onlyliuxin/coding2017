package com.coding.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.ArrayList;
import com.coding.basic.Iterator;

public class IteratorTest {

	Iterator it ;
	
	@Before
	public void setUp() throws Exception {
		ArrayList list = new ArrayList();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		it = list.iterator();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}

}
