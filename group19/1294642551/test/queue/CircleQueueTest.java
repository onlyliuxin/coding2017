package queue;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CircleQueueTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEnQueue(){
		CircleQueue<Integer> queue = new CircleQueue<Integer>(6);
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.enQueue(4);
		queue.enQueue(5);
		Assert.assertEquals("[1, 2, 3, 4, 5]", queue.toString());
		
	}
	
	@Test
	public void testDeQueue(){
		CircleQueue<Integer> queue = new CircleQueue<Integer>(6);
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.enQueue(4);
		queue.enQueue(5);
		queue.deQueue();
		Assert.assertEquals("[2, 3, 4, 5]", queue.toString());
	}
	
	@Test
	public void testSet(){
		CircleQueue<Integer> queue = new CircleQueue<Integer>(6);
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.enQueue(4);
		queue.enQueue(5);
		queue.deQueue();
		queue.deQueue();
		Assert.assertEquals("[3, 4, 5]", queue.toString());
		queue.set(2, 8);
		Assert.assertEquals("[8, 4, 5]", queue.toString());
	}
	
	@Test
	public void testGet(){
		CircleQueue<Integer> queue = new CircleQueue<Integer>(6);
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.enQueue(4);
		queue.enQueue(5);
		int actual = queue.get(3);
		Assert.assertEquals(4, actual);
	}
	
	public void testSize(){
		CircleQueue<Integer> queue = new CircleQueue<Integer>(6);
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.enQueue(4);
		queue.enQueue(5);
		Assert.assertEquals(5, queue.size());
	}

}
