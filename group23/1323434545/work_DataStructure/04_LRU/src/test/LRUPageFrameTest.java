package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.LRUPageFrame;

public class LRUPageFrameTest {

	@Test
	public void testAccess() {
		LRUPageFrame pf = new LRUPageFrame(3);
		pf.access(7);
		pf.access(0);
		pf.access(1);
		assertEquals("1,0,7", pf.toString());
		pf.access(2);
		assertEquals("2,1,0", pf.toString());
		pf.access(0);
		assertEquals("0,2,1", pf.toString());
		pf.access(0);
		assertEquals("0,2,1", pf.toString());
		pf.access(3);
		assertEquals("3,0,2", pf.toString());
		pf.access(0);
		assertEquals("0,3,2", pf.toString());
		pf.access(4);
		assertEquals("4,0,3", pf.toString());
		pf.access(5);
		assertEquals("5,4,0", pf.toString());

	}

}
