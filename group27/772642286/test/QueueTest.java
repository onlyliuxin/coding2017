package week01.test;

import org.junit.Assert;
import org.junit.Before;

import week01.basic.Queue;

public class QueueTest {
	
	private Queue queue;
	
	@Before
	public void init(){
		queue = new Queue();
		for(int i=1;i<=500;i++){
			queue.enQueue(i);
  		}
	}
	
	public void enQueueTest(){	
		Assert.assertEquals(500, queue.size());
	}
	
	public void deQueue(){
		for(int i=500;i>=1 ;i--){
			Assert.assertEquals(i, queue.deQueue());
		}
		
	}
	
	public void isEmpty(){
		Assert.assertEquals(false, queue.isEmpty());
		for(int i=500;i>=1 ;i--){
			Assert.assertEquals(i, queue.deQueue());
		}
		Assert.assertEquals(true, queue.isEmpty());
	}
	
	public void size(){
		for(int i=499;i>0 ;i--){
			queue.deQueue();
			Assert.assertEquals(i, queue.size());
		}
	}
}
