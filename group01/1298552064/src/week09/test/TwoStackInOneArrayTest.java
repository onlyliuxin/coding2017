package week09.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week09.basic.TwoStackInOneArray;

public class TwoStackInOneArrayTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		TwoStackInOneArray ts = new TwoStackInOneArray(5);
		ts.push1(1);
		ts.push1(2);
		
		ts.push2(5);
		ts.push2(6);
		ts.push2(7);
		
		Assert.assertEquals("[1,2,7,6,5]", ts.toString());
		
		Object o = ts.peek1();
		Assert.assertEquals(2, o);
		
		o = ts.peek2();
		Assert.assertEquals(7, o);
		
		Assert.assertEquals(2, ts.pop1());
		Assert.assertEquals(7, ts.pop2());
		
		Assert.assertEquals("[1,null,null,6,5]", ts.toString());
		
		ts.push1(2);
		ts.push1(3);
		
		ts.push2(7);
		Assert.assertEquals("[1,2,3,null,7,6,5]", ts.toString());
	}
}
