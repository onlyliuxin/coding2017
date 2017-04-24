package com.ralf.lru;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LRUPageFrameTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		LRUPageFrame frame = new LRUPageFrame(3);
		frame.access(7);
		frame.access(0);
		frame.access(1);
		Assert.assertEquals("1,0,7", frame.toString());
		frame.access(2);
		Assert.assertEquals("2,1,0", frame.toString());
		frame.access(0);
		
		Assert.assertEquals("0,2,1", frame.toString());
		
		frame.access(0);
		Assert.assertEquals("0,2,1", frame.toString());
		frame.access(3);
		Assert.assertEquals("3,0,2", frame.toString());
		frame.access(0);
		Assert.assertEquals("0,3,2", frame.toString());
		frame.access(4);
		Assert.assertEquals("4,0,3", frame.toString());
	}

}
