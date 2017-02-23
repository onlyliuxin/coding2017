package week01.BasicDataStructureTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week01.BasicDataStructure.Queue;


public class QueueTest {
	
	Queue queue = new Queue();

	@Before
	public void setUp() throws Exception {
		for(int i=0;i<100;i++){
			queue.enQueue(i);
		}
	}

	@Test
	public void testEnQueue() {
		Assert.assertEquals(queue.size(), 100);
		for(int i =0;i<100;i++){
			queue.enQueue(i);
		}
		Assert.assertEquals(queue.size(), 200);
	}

	@Test
	public void testDeQueue() {
		for(int i =0;i<100;i++){
			Assert.assertEquals(queue.deQueue(), i);
		}
		
	}

	@Test
	public void testIsEmpty() {
		Assert.assertEquals(queue.isEmpty(), false);
		for(int i=0;i<100;i++){
			queue.deQueue();
		}
		Assert.assertEquals(queue.isEmpty(), true);
	}

	@Test
	public void testSize() {
		Assert.assertEquals(queue.size(), 100);
		queue.enQueue(100);
		Assert.assertEquals(queue.size(), 101);
		queue.deQueue();
		Assert.assertEquals(queue.size(), 100);
	}

}
