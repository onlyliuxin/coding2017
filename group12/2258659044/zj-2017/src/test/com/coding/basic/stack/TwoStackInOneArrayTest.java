package test.com.coding.basic.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.stack.TwoStackInOneArray;

public class TwoStackInOneArrayTest {

	
	TwoStackInOneArray stack;
	
	@Before
	public void setUp() throws Exception {
		
		stack = new TwoStackInOneArray();
	}

	//@Test(expected = EmptyStackException.class)
	public void testPush1() {
		
		stack.push1("zj");
		Assert.assertEquals("zj", stack.peek1());
		
		stack.push1("hy");
		Assert.assertEquals("hy", stack.peek1());
		
		stack.pop1();
		stack.pop1();
		
		stack.peek1();		
	}

	//@Test(expected = EmptyStackException.class)
	public void testPop1() {
		
		stack.push1("zj");
		Assert.assertEquals("zj", stack.pop1());
		stack.pop1();
		
	}

	//@Test
	public void testPeek1() {
		
		stack.push1("zj");
		Assert.assertEquals("zj", stack.peek1());
		
		stack.push1("hh");
		stack.push1("hy");		
		Assert.assertEquals("hy", stack.peek1());
		
	}

	//@Test(expected = EmptyStackException.class)
	public void testPush2() {
		
		stack.push2("zj");
		Assert.assertEquals("zj", stack.peek2());
		
		stack.push2("hy");
		Assert.assertEquals("hy", stack.peek2());
		
		stack.pop2();
		stack.pop2();
		
		stack.peek2();	
	}

	//@Test(expected = EmptyStackException.class)
	public void testPop2() {
		
		stack.push2("zj");
		Assert.assertEquals("zj", stack.pop2());
		stack.pop2();
	}

	//@Test
	public void testPeek2() {
		
		stack.push2("zj");
		Assert.assertEquals("zj", stack.peek2());
		
		stack.push2("hh");
		stack.push2("hy");		
		Assert.assertEquals("hy", stack.peek2());
	}

	@Test
	public void testGrow() throws Exception{
		
		for (int i = 0; i < 5; i++) {
			stack.push1(i);
		}
		
		for (int i = 0; i < 5; i++) {
			stack.push2(i);
		}
		Assert.assertEquals(4, stack.peek1());
		Assert.assertEquals(4, stack.peek2());
		
		//重置栈
		setUp();		
		for (int i = 0; i < 11; i++) {
			stack.push1(i);
		}
		Assert.assertEquals(10, stack.peek1());
		
		setUp();
		for (int i = 0; i < 11; i++) {
			stack.push2(i);
		}
		Assert.assertEquals(10, stack.peek2());
		
		setUp();
		for (int i = 0; i < 5; i++) {
			stack.push1(i);
		}
		for (int i = 0; i < 10; i++) {
			stack.push2(i);
		}
		Assert.assertEquals(4, stack.peek1());
		Assert.assertEquals(9, stack.peek2());
		stack.pop1();
		stack.pop2();
		Assert.assertEquals(3, stack.peek1());
		Assert.assertEquals(8, stack.peek2());
	}
}
