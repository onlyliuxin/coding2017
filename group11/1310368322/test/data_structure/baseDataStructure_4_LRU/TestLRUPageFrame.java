package DataStructure_4_LRU;

import static org.junit.Assert.*;
import org.junit.*;

import org.junit.Test;

public class TestLRUPageFrame {

	@Test
	public void testAccess() {
		LRUPageFrame frame = new LRUPageFrame(3);// 物理页面存储容量为3个物理页面
		frame.access(7);
		frame.access(0);
		frame.access(1);
		Assert.assertEquals("1,0,7",frame.toString());
		frame.access(2);
		Assert.assertEquals("2,1,0",frame.toString());
		frame.access(0);
		Assert.assertEquals("0,2,1", frame.toString());
		frame.access(0);
		Assert.assertEquals("0,2,1",frame.toString());
		frame.access(3);
		Assert.assertEquals("3,0,2",frame.toString());
		frame.access(0);
		Assert.assertEquals("0,3,2",frame.toString());
		frame.access(4);
		Assert.assertEquals("4,0,3",frame.toString());
	}

}
