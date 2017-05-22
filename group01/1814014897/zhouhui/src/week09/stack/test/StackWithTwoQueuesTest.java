package week09.stack.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week09.stack.StackWithTwoQueues;


public class StackWithTwoQueuesTest {
	
	StackWithTwoQueues<Integer> swtq = new StackWithTwoQueues<Integer>();

	@Before
	public void setUp() throws Exception {
		for(int i=0;i<100;i++){
			swtq.push(i);
		}
	}

	@Test
	public void testPush() {
		Assert.assertFalse(swtq.isEmpty());
		Assert.assertEquals(100, swtq.size());
		
		for(int i=100;i<200;i++){
			swtq.push(i);
		}
		
		Assert.assertEquals(200, swtq.size());
		for(int i=199;i>=0;i--){
			Assert.assertEquals(i, swtq.pop().intValue());
		}
		Assert.assertEquals(0, swtq.size());
	}

	@Test
	public void testPop() {
		for(int i=99;i>=0;i--){
			Assert.assertEquals(i, swtq.pop().intValue());
		}
		Assert.assertTrue(swtq.isEmpty());
		Assert.assertEquals(0, swtq.size());
	}

}
