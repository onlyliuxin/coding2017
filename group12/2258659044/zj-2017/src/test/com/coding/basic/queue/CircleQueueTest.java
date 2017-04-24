package test.com.coding.basic.queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.queue.CircleQueue;

public class CircleQueueTest {

	CircleQueue<Object> qe;;
	
	@Before
    public void setup() {
		qe = new CircleQueue<>();		
    }
	
	@Test
	public void testIsEmpty() {
		
		Assert.assertEquals(true, qe.isEmpty());
	}

	@Test
	public void testSize() {
		
		for (int i = 0; i < 9; i++) {
			qe.enQueue(i);
		}
		Assert.assertEquals(9, qe.size());
	}

	@Test//(expected = IndexOutOfBoundsException.class)
	public void testEnQueue() {
		
		for (int i = 0; i < 7; i++) {
			qe.enQueue(i);
		}
		Assert.assertEquals("[0,1,2,3,4,5,6]", qe.toString());
		
		//打开下面测试一场
		/*for (int i = 7; i < 10; i++) {
			qe.enQueue(i);
		}*/
	}

	@Test
	public void testDeQueue() {
		
		for (int i = 0; i < 9; i++) {
			qe.enQueue(i);
		}
		Assert.assertEquals(9,qe.size());
		
		Assert.assertEquals(0,qe.deQueue());
		Assert.assertEquals("[1,2,3,4,5,6,7,8]", qe.toString());
		Assert.assertEquals(8,qe.size());
		
		Assert.assertEquals(1,qe.deQueue());
		Assert.assertEquals("[2,3,4,5,6,7,8]", qe.toString());
		Assert.assertEquals(7,qe.size());
		
		qe.enQueue(1);
		Assert.assertEquals("[2,3,4,5,6,7,8,1]", qe.toString());
		Assert.assertEquals(8,qe.size());
		
		qe.enQueue(0);
		Assert.assertEquals("[2,3,4,5,6,7,8,1,0]", qe.toString());
		Assert.assertEquals(9,qe.size());
		
		for (int i = 0; i < 9; i++) {
			qe.deQueue();
			qe.enQueue(i);
		}
		Assert.assertEquals(9,qe.size());
		
		qe.deQueue();qe.deQueue();
		Assert.assertEquals(7,qe.size());
		
		qe.enQueue(4);
		Assert.assertEquals(8,qe.size());
		
		qe.enQueue(4);
		Assert.assertEquals(9,qe.size());
	}

}
