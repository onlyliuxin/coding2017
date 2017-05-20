package com.coding.basic.queue;

import org.junit.Assert;
import org.junit.Test;

public class JosephusTest {

	@Test
	public void testExecute() {
		Assert.assertEquals("[1, 3, 5, 0, 4, 2, 6]", Josephus.execute(7, 2).toString());	
	}
}
