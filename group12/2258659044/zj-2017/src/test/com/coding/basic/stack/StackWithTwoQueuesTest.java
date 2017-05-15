package test.com.coding.basic.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.stack.StackWithTwoQueues;

public class StackWithTwoQueuesTest {

	StackWithTwoQueues<Object> stack;
	
	@Before
	public void setUp() throws Exception {
		
		stack = new StackWithTwoQueues<>();
	}

	@Test
	public void testPush() {
		
		stack.push(123);
	    Assert.assertEquals(1, stack.size());
	    
	    stack.push("zj");
	    stack.push('Y');
	    Assert.assertEquals(3, stack.size());
	    
	}

	@Test
	public void testPop() {
		
		stack.push(123);				
		stack.push("zj");
		Assert.assertEquals("zj", stack.pop());
		Assert.assertEquals(1, stack.size());
		
	    stack.push('H');
	    stack.push('Y');
	    Assert.assertEquals('Y', stack.pop());
	    Assert.assertEquals(2, stack.size());
	    
	}

	@Test
	public void testSize() {
		
		Assert.assertEquals(0, stack.size());
		
		stack.push("zj");
		stack.push('H');
	    stack.push('y');		
	    Assert.assertEquals(3, stack.size());
	    
	    stack.pop();
	    Assert.assertEquals(2, stack.size());
	}

	@Test
	public void testIsEmpty() {
		
		Assert.assertEquals(true, stack.isEmpty());
		
		stack.push("zj");
		stack.push('H');
	    stack.push('y');
	    
	    Assert.assertEquals(false, stack.isEmpty());
	}

}
