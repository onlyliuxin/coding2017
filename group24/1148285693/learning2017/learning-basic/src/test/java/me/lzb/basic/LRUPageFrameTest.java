package me.lzb.basic;

import me.lzb.basic.LRUPageFrame;
import org.junit.Assert;
import org.junit.Test;


public class LRUPageFrameTest {

	@Test
	public void testAccess() {
		LRUPageFrame frame = new LRUPageFrame(3);
		frame.access(7);
        Assert.assertEquals("7", frame.toString());
        Assert.assertEquals("7", frame.toStringDESC());
		frame.access(0);
        Assert.assertEquals("0,7", frame.toString());
        Assert.assertEquals("7,0", frame.toStringDESC());
		frame.access(1);
		Assert.assertEquals("1,0,7", frame.toString());
        Assert.assertEquals("7,0,1", frame.toStringDESC());
		frame.access(2);
		Assert.assertEquals("2,1,0", frame.toString());
        Assert.assertEquals("0,1,2", frame.toStringDESC());
		frame.access(0);
		Assert.assertEquals("0,2,1", frame.toString());
        Assert.assertEquals("1,2,0", frame.toStringDESC());
		frame.access(0);
		Assert.assertEquals("0,2,1", frame.toString());
        Assert.assertEquals("1,2,0", frame.toStringDESC());
		frame.access(3);
		Assert.assertEquals("3,0,2", frame.toString());
        Assert.assertEquals("2,0,3", frame.toStringDESC());
		frame.access(0);
		Assert.assertEquals("0,3,2", frame.toString());
        Assert.assertEquals("2,3,0", frame.toStringDESC());
		frame.access(4);
		Assert.assertEquals("4,0,3", frame.toString());
        Assert.assertEquals("3,0,4", frame.toStringDESC());
	}

}
