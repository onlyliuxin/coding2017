package stack;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StackWithTwoQueuesTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		StackWithTwoQueues<Integer> stack = new StackWithTwoQueues<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		Assert.assertEquals(3, stack.size());
		
		int actual = stack.pop();
		Assert.assertEquals(3, actual);
		
		actual = stack.pop();
		Assert.assertEquals(2, actual);
		
		actual = stack.pop();
		Assert.assertEquals(1, actual);
		
		Assert.assertEquals(true, stack.isEmpty());
	}



}
