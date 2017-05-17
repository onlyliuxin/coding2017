package stack;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TwoStackInOneArrayTest {


	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		TwoStackInOneArray stack = new TwoStackInOneArray(6);
		stack.push1(1);
		stack.push1(2);
		stack.push1(3);
		stack.push2("a");
		stack.push2("b");
		stack.push2("c");
		Assert.assertEquals("[1, 2, 3, c, b, a]", stack.toString());
		
		stack.pop1();
		stack.pop2();
		Assert.assertEquals("[1, 2, null, null, b, a]", stack.toString());
		
		stack.push2("d");
		stack.push2("e");
		Assert.assertEquals("[1, 2, e, d, b, a]", stack.toString());
		Assert.assertEquals(true, stack.isFull());
		
		stack.push1("4");
		Assert.assertEquals("[1, 2, 4, null, null, null, null, null, e, d, b, a]", stack.toString());
	}

}
