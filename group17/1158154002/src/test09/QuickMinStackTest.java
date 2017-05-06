package test09;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QuickMinStackTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExecute() {
			QuickMinStack quick=new QuickMinStack();
			quick.push(6);
			quick.push(3);
			quick.push(8);
			quick.push(7);
			quick.push(2);
			quick.push(5);
			quick.push(-1);
			quick.push(1);
			
			System.out.println(quick.findMin());
			quick.pop();
			quick.pop();
			System.out.println(quick.findMin());
	}
}
