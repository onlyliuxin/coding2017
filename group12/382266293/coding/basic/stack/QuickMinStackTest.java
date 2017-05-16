package stack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuickMinStackTest {

	QuickMinStack qs;
	
	@Before
	public void setUp() throws Exception {
		qs = new QuickMinStack();
	}

	@After
	public void tearDown() throws Exception {
		qs = null;
	}

	@Test
	public void testPush() {
		qs.push(1);
		qs.push(2);
	}

	@Test
	public void testPop() {
		qs.push(1);
		qs.push(2);
		assertEquals(2,qs.pop());
		assertEquals(1,qs.pop());
	}

	@Test
	public void testSize() {
		assertEquals(0,qs.size());
		qs.push(1);
		qs.push(2);
		assertEquals(2,qs.size());
	}

	@Test
	public void testFindMin() {
		for (int i = 0; i < 8; i++) {
			qs.push(i);
		}
		
		qs.push(-19);
		qs.push(100);
		
		assertEquals(-19,qs.findMin());
		qs.pop();
		qs.pop();
		assertEquals(0,qs.findMin());
		qs.push(-1);
		qs.push(-1);
		assertEquals(-1,qs.findMin());
		qs.pop();
		assertEquals(-1,qs.findMin());
		qs.pop();
		assertEquals(0,qs.findMin());
		
	}

}
