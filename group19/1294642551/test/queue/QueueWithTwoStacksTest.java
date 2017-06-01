package queue;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QueueWithTwoStacksTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		QueueWithTwoStacks<Integer> queue = new QueueWithTwoStacks<Integer>();
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		
		int actual = queue.deQueue();
		Assert.assertEquals(1, actual);
		
		actual = queue.deQueue();
		Assert.assertEquals(2, actual);
		
		actual = queue.deQueue();
		Assert.assertEquals(3, actual);
		
		Assert.assertEquals(queue.isEmpty(), true);
	}

}
