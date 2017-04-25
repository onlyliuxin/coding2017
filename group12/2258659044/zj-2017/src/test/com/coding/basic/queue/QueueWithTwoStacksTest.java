package test.com.coding.basic.queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.queue.QueueWithTwoStacks;

public class QueueWithTwoStacksTest {

	private QueueWithTwoStacks<Object> queue;
	
	@Before
	public void setUp() throws Exception {
		queue = new QueueWithTwoStacks<>();
	}

	@Test
	public void testIsEmpty() {
		
		Assert.assertEquals(true, queue.isEmpty());
	}

	@Test
	public void testSize() {
		
		Assert.assertEquals(0, queue.size());
		
		queue.enQueue("zj");
		Assert.assertEquals(1, queue.size());
		
		for (int i = 0; i < 10; i++) {
			queue.enQueue(i);
		}
		Assert.assertEquals(11, queue.size());
		
		for (int i = 0; i < 5; i++) {
			queue.deQueue();
		}
		Assert.assertEquals(6, queue.size());
	}

	@Test
	public void testEnQueue() {
		
		for (int i = 0; i < 10; i++) {
			queue.enQueue(i);
		}
		
		for (int i = 0; i < 10; i++) {
			Assert.assertEquals(i, queue.deQueue());
		}
	}

	@Test
	public void testDeQueue() {
		
		Assert.assertEquals(null, queue.deQueue());
		
		for (int i = 0; i < 10; i++) {
			queue.enQueue(i);
		}
		
		for (int i = 0; i < 5; i++) {
			queue.deQueue();
		}
		Assert.assertEquals(5, queue.size());
		Assert.assertEquals(false, queue.isEmpty());
		
	}

}
