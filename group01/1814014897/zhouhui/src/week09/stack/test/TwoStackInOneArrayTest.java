package week09.stack.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week09.stack.TwoStackInOneArray;


public class TwoStackInOneArrayTest {
	
	TwoStackInOneArray tsioa = new TwoStackInOneArray();

	@Before
	public void setUp() throws Exception {
		for(int i=0;i<10;i++){
			tsioa.push1(i);
		}
		for(int j=20;j<30;j++){
			tsioa.push2(j);
		}
	}

	@Test
	public void testPush1() {
		for(int i=10;i<20;i++){
			tsioa.push1(i);
		}
		for(int i=19;i>=0;i--){
			Assert.assertEquals(i, tsioa.pop1());
		}
	}

	@Test
	public void testPop1() {
		for(int i=9;i>=0;i--){
			Assert.assertEquals(i, tsioa.pop1());
		}
	}

	@Test
	public void testPeek1() {
		Assert.assertEquals(9, tsioa.peek1());
		tsioa.pop1();
		Assert.assertEquals(8, tsioa.peek1());
		tsioa.pop1();
		Assert.assertEquals(7, tsioa.peek1());
	}

	@Test
	public void testPush2() {
		for(int i=30;i<40;i++){
			tsioa.push2(i);
		}
		for(int i=39;i>=20;i--){
			Assert.assertEquals(i, tsioa.pop2());
		}
	}

	@Test
	public void testPop2() {
		for(int i=29;i>=20;i--){
			Assert.assertEquals(i, tsioa.pop2());
		}
	}

	@Test
	public void testPeek2() {
		Assert.assertEquals(29, tsioa.peek2());
		tsioa.pop2();
		Assert.assertEquals(28, tsioa.peek2());
		tsioa.pop2();
		Assert.assertEquals(27, tsioa.peek2());
	}

}
