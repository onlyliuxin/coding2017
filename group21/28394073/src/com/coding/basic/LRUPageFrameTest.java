package com.coding.basic;

import junit.framework.Assert;
import org.junit.Test;

public class LRUPageFrameTest {
	
//	@Before
//	public void setUp() throws Exception {
//		
//		
//		
//	}

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
		
		LRUPageFrame frame1 = new LRUPageFrame(5);
		frame1.access(1);
		frame1.access(2);
		frame1.access(1);
		frame1.access(3);
		frame1.access(2);
		Assert.assertEquals("-1,-1,2,3,1", frame1.toString());
		frame1.access(0);
		Assert.assertEquals("-1,0,2,3,1", frame1.toString());
		frame1.access(0);
		Assert.assertEquals("-1,0,2,3,1", frame1.toString());
		frame1.access(3);
		Assert.assertEquals("-1,3,0,2,1", frame1.toString());
		frame1.access(5);
		Assert.assertEquals("5,3,0,2,1", frame1.toString());
		frame1.access(0);
		Assert.assertEquals("0,5,3,2,1", frame1.toString());
		frame1.access(4);
		Assert.assertEquals("4,0,5,3,2", frame1.toString());
		frame1.access(2);
		Assert.assertEquals("2,4,0,5,3", frame1.toString());
	}

//	@Test
//	public void testPush() {
//		LRUPageFrame frame1 = new LRUPageFrame(3);
//		frame1.push(1);
//		frame1.push(2);
//		frame1.push(3);
//		Assert.assertEquals("1,2,3", frame1.toString());
//	}
//
//	@Test
//	public void testPop() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetTop() {
//		fail("Not yet implemented");
//	}

}
