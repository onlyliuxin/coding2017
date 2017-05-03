package com.github.miniyk2012.coding2017.basic.linklist;

import  org.junit.Assert;

import org.junit.Test;


public class LRUPageFrameTest {
	
	@Test
	public void testAccess() {
		LRUPageFrame frame = new LRUPageFrame(3);
		frame.access(7);
		frame.access(0);
		Assert.assertEquals("0,7", frame.toString());
		Assert.assertEquals("7,0", frame.lastToString());
		frame.access(1);
		Assert.assertEquals("1,0,7", frame.toString());
		Assert.assertEquals("7,0,1", frame.lastToString());
		frame.access(2);
		Assert.assertEquals("2,1,0", frame.toString());
		Assert.assertEquals("0,1,2", frame.lastToString());
		frame.access(0);
		Assert.assertEquals("0,2,1", frame.toString());
		Assert.assertEquals("1,2,0", frame.lastToString());
		frame.access(0);
		Assert.assertEquals("0,2,1", frame.toString());
		Assert.assertEquals("1,2,0", frame.lastToString());
		frame.access(3);
		Assert.assertEquals("3,0,2", frame.toString());
		Assert.assertEquals("2,0,3", frame.lastToString());
		frame.access(0);
		Assert.assertEquals("0,3,2", frame.toString());
		Assert.assertEquals("2,3,0", frame.lastToString());
		frame.access(4);
		Assert.assertEquals("4,0,3", frame.toString());
		Assert.assertEquals("3,0,4", frame.lastToString());
		frame.access(4);
		Assert.assertEquals("4,0,3", frame.toString());
		Assert.assertEquals("3,0,4", frame.lastToString());
		frame.access(4);
		Assert.assertEquals("4,0,3", frame.toString());
		Assert.assertEquals("3,0,4", frame.lastToString());
		frame.access(0);
		Assert.assertEquals("0,4,3", frame.toString());
		Assert.assertEquals("3,4,0", frame.lastToString());
		frame.access(7);
		Assert.assertEquals("7,0,4", frame.toString());
		Assert.assertEquals("4,0,7", frame.lastToString());
		frame.access(4);
		Assert.assertEquals("4,7,0", frame.toString());
		Assert.assertEquals("0,7,4", frame.lastToString());

		LRUPageFrame frame2 = new LRUPageFrame(1);
		Assert.assertEquals("", frame2.toString());
		frame2.access(7);
		Assert.assertEquals("7", frame2.toString());
		frame2.access(0);
		Assert.assertEquals("0", frame2.toString());
	}

}
