package com.coding.basic.linklist;

import  org.junit.Assert;

import org.junit.Test;


public class LRUPageFrameTest {
	
	@Test
	public void testAccess() {
		LRUPageFrame frame = new LRUPageFrame(3);
		frame.put(1,0);
		frame.put(0,1);
		frame.put(7,2);
		frame.get(7);
		frame.get(0);
		frame.get(1);
		Assert.assertEquals("1,0,7", frame.toString());
		frame.put(2,0);
		Assert.assertEquals("2,1,0", frame.toString());
		frame.put(0,1);
		Assert.assertEquals("0,2,1", frame.toString());
		frame.put(0,1);
		Assert.assertEquals("0,2,1", frame.toString());
		frame.put(3,1);
		Assert.assertEquals("3,0,2", frame.toString());
		frame.put(0,1);
		Assert.assertEquals("0,3,2", frame.toString());
		frame.put(4,1);
		Assert.assertEquals("4,0,3", frame.toString());
	}

}