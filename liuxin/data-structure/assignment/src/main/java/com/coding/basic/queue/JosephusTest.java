package com.coding.basic.queue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class JosephusTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExecute() {
		
		Assert.assertEquals("[1, 3, 5, 0, 4, 2, 6]", Josephus.execute(7, 2).toString());
		
	}

}
