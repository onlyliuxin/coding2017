package stack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TwoStackInOneArrayTest {

	TwoStackInOneArray ts;
	@Before
	public void setUp() throws Exception {
		ts = new TwoStackInOneArray();
	}

	@After
	public void tearDown() throws Exception {
		ts = null;
	}

	@Test
	public void testPush1() {
		ts.push1(1);
		ts.push1(2);
	}

	@Test
	public void testSize1() {
		assertEquals(0,ts.size1());
		ts.push1(1);
		ts.push1(2);
		assertEquals(2,ts.size1());
	}

	@Test
	public void testSize2() {
		assertEquals(0,ts.size2());
		ts.push2(1);
		ts.push2(2);
		assertEquals(2,ts.size2());
	}
	
	@Test
	public void testSize12() {
		assertEquals(0,ts.size1());
		ts.push1(1);
		ts.push1(2);
		assertEquals(2,ts.size1());
		assertEquals(0,ts.size2());
		ts.push2(1);
		ts.push2(2);
		assertEquals(2,ts.size2());
	}

	@Test
	public void testPush2() {
		ts.push2(1);
		ts.push2(2);
		assertEquals(2,ts.pop2());
		assertEquals(1,ts.pop2());
	}
	
	@Test
	public void testPush12() {
		ts.push1(1);
		ts.push1(2);
		assertEquals(2,ts.pop1());
		assertEquals(1,ts.pop1());
		ts.push2(1);
		ts.push2(2);
		assertEquals(2,ts.pop2());
		assertEquals(1,ts.pop2());
	}
	

	@Test
	public void testPop1() {
		
		ts.push1(1);
		ts.push1(2);
		assertEquals(2,ts.pop1());
		assertEquals(1,ts.pop1());
		
	}
	


	@Test
	public void testPop2() {
		
		ts.push2(1);
		ts.push2(2);
		assertEquals(2,ts.pop2());
		assertEquals(1,ts.pop2());
		
	}

	@Test
	public void testPeek2() {
		
		ts.push1(1);
		ts.push1(2);
		assertEquals(2,ts.pop1());
		assertEquals(1,ts.pop1());
		
		ts.push2(1);
		ts.push2(2);
		assertEquals(2,ts.pop2());
		assertEquals(1,ts.pop2());
	}

	@Test
	public void testPeek1() {
		
		ts.push1(1);
		assertEquals(1,ts.peek1());
		ts.push1(2);
		assertEquals(2,ts.peek1());
		
	}
	
	@Test
	public void testGrow() {
		
		for(int i = 1; i < 101; i++) {
			ts.push1(i);
			ts.push2(i);
			assertEquals(i,ts.size1());
			assertEquals(i,ts.size2());
		}
		
		for (int i = 100; i >0; i--) {
			assertEquals(i, ts.pop1());
			assertEquals(i, ts.pop2());
		}
		
	}



}
