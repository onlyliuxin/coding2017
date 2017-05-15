package com.dudy.learn01.data_structure.queue;
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
		System.out.println(Josephus.execute(7, 2).toString());
		Assert.assertEquals("[1, 3, 5, 0, 4, 2, 6]", Josephus.execute(7, 2).toString());
		
	}

}