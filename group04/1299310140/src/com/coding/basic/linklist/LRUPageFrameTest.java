package com.coding.basic.linklist;

import  org.junit.Assert;
import org.junit.Test;

public class LRUPageFrameTest {
	
	@Test
	public void testAccess() {
		LRUPageFrame frame = new LRUPageFrame(3);
		frame.access(7);
		frame.access(7);
		frame.access(0);
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
		
		LRUPageFrame frameFive = new LRUPageFrame(5);
		frameFive.access(7);//7
		frameFive.access(7);//7
		frameFive.access(0);//0 7
		frameFive.access(7);//7 0
		frameFive.access(0);//0 7
		frameFive.access(1);//1 0 7
		Assert.assertEquals("1,0,7", frameFive.toString());
		frameFive.access(2);//2 1 0 7
		Assert.assertEquals("2,1,0,7", frameFive.toString());
		frameFive.access(0);//0 2 1 7
		Assert.assertEquals("0,2,1,7", frameFive.toString());
		frameFive.access(0);//0 2 1 7
		Assert.assertEquals("0,2,1,7", frameFive.toString());
		frameFive.access(3);//3 0 2 1 7
		Assert.assertEquals("3,0,2,1,7", frameFive.toString());
		frameFive.access(0);//0 3 2 1 7
		Assert.assertEquals("0,3,2,1,7", frameFive.toString());
		frameFive.access(4);//4 0 3 2 1
		Assert.assertEquals("4,0,3,2,1", frameFive.toString());
	}
	
//	@Test
//	public void testAddFirst(){
//		LRUPageFrame frame = new LRUPageFrame(3);
//		frame.addFirst(1);
//		frame.addFirst(2);
//		Assert.assertEquals("2,1", frame.toString());
//		frame.addFirst(3);
//		Assert.assertEquals("3,2,1", frame.toString());
//		frame.addFirst(4);
//		Assert.assertEquals("4,3,2,1", frame.toString());
//		frame.removeElement(3);
//		Assert.assertEquals("4,2,1", frame.toString());
//		frame.removeElement(1);
//		Assert.assertEquals("4,2", frame.toString());
//		frame.removeElement(4);
//		Assert.assertEquals("2", frame.toString());
//		frame.removeElement(2);
//		Assert.assertEquals("", frame.toString());
//	}
	
}
