package stack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StackWithTwoQueuesTest {

	StackWithTwoQueues<Integer> sq; 
	@Before
	public void setUp() throws Exception {
		sq = new StackWithTwoQueues<>();
	}

	@After
	public void tearDown() throws Exception {
		sq = null;
	}
	
	@Test
	public void testIsEmpty() {
		assertEquals(true,sq.isEmpty());
	}

	@Test
	public void testPush() {
		sq.push(1);
		assertEquals(1,sq.size());
		sq.push(2);
		assertEquals(2,sq.size());
		sq.pop();
		assertEquals(1,sq.size());
		sq.pop();
		assertEquals(0,sq.size());
	}

	@Test
	public void testPop() {
		sq.push(1);
		sq.push(2);
		sq.push(-3);
		assertEquals(-3,sq.pop());
		sq.push(4);
		assertEquals(4,sq.pop());
		assertEquals(2,sq.pop());
		assertEquals(1,sq.pop());
	}

}
