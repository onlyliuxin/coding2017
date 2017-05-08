package linklist;

import  org.junit.Assert;

import org.junit.Test;


public class LRUPageFrameTest {
	
	@Test
	public void testAccess() {
		LRUPageFrame frame = new LRUPageFrame(4);
		frame.access(7);
		frame.access(0);
		frame.access(1);
		frame.access(9);
		Assert.assertEquals("9,1,0,7", frame.toString());
		frame.access(2);
		Assert.assertEquals("2,9,1,0", frame.toString());
		frame.access(0);
		Assert.assertEquals("0,2,9,1", frame.toString());
		frame.access(0);
		Assert.assertEquals("0,2,9,1", frame.toString());
		frame.access(3);
		Assert.assertEquals("3,0,2,9", frame.toString());
		frame.access(0);
		Assert.assertEquals("0,3,2,9", frame.toString());
		frame.access(4);
		Assert.assertEquals("4,0,3,2", frame.toString());
	}

}
