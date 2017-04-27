package test.com.coding.basic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.Queue;

public class QueueTest {

	Queue qe ;
	
	@Before
    public void setup() {
		qe = new Queue();
		for (int i = 0; i < 10; i++) {
			qe.enQueue(i);
		}		
    }

	@Test
	public void enQueue(){
		
		Assert.assertEquals(qe.size(), 10);
		qe.enQueue("abcd");
		Assert.assertEquals(qe.size(), 11);
	}
	
	@Test//(expected = IndexOutOfBoundsException.class)
	public void deQueue(){
		
		Assert.assertEquals(qe.size(), 10);		
		for (int i = 0; i < 10; i++) {
			Assert.assertEquals(qe.deQueue(), i);
		}
		Assert.assertEquals(qe.size(), 0);
		//打开下列语句与期望异常测试
		//qe.deQueue();
	}
	
	public void isEmpty(){
		
		Assert.assertEquals(qe.isEmpty(),false);
		for (int i = 0; i < 10; i++) {
			qe.deQueue();
		}
		Assert.assertEquals(qe.isEmpty(),true);
		Queue qe1 = new Queue();
		Assert.assertEquals(qe1.isEmpty(), true);
	}
	
	public void size(){
		
		Assert.assertEquals(qe.size(),10);
		qe.enQueue("lk");
		qe.enQueue('h');
		Assert.assertEquals(qe.size(),12);
		for (int i = 0; i < 12; i++) {
			qe.deQueue();
		}
		Assert.assertEquals(qe.size(),0);
		Queue qe1 = new Queue();
		Assert.assertEquals(qe1.size(), 0);
	}
}
