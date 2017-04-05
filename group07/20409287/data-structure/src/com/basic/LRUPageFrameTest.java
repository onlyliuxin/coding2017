package com.basic;

import  org.junit.Assert;

import org.junit.Test;


public class LRUPageFrameTest {
	
	@Test
	public void testAccess() {
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

	@Test
	public void testAccess2() {

		LRUPageFrame frame = new LRUPageFrame(10000);
		for (int i = 0; i < 10000; i++) {
			frame.access(i);
		}
		Assert.assertEquals(9999, frame.getFirst());
		for (int i = 9999; i >- 0; i--) {
			frame.access(i);
			Assert.assertEquals(i, frame.getFirst());
		}

		for (int i = 4; i < 100; i++) {
			frame.access(i);
			Assert.assertEquals(i, frame.getFirst());
		}

		for (int i = 20000; i < 30000; i++) {
			frame.access(i);
			Assert.assertEquals(i, frame.getFirst());
		}
	}

}
