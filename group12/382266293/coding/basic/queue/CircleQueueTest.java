package queue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class CircleQueueTest {

	CircleQueue cq = new CircleQueue();

    @After  
    public void tearDown() throws Exception {
    	cq = null;
    }
    
	@Test
	public void testIsEmpty() {
		assertEquals(cq.size(),0);
	}

	@Test
	public void testSize() {
		for (int i = 0; i < 10; i++) {
			cq.enQueue(i);
		}
		
		cq.deQueue();
		cq.deQueue();
		System.out.println(cq);
		cq.enQueue(10);
		cq.enQueue(11);
		assertEquals(10,cq.size());
		System.out.println(cq);
		
		int i = 2;
		while(cq.size() > 0) {
			assertEquals(i++, cq.deQueue());
		}
	}

	@Test
	public void testEnQueue() {
		for (int i = 0; i < 5; i++) {
			cq.enQueue(i);
		}
		assertEquals(5,cq.size());
	}

	@Test
	public void testDeQueue() {
		for (int i = 0; i < 10; i++) {
			cq.enQueue(i);
		}
		
		int i = 0;
		while(cq.size() > 0) {
			assertEquals(i++, cq.deQueue());
		}
		
		
	}

}
