package week1.collectiontest;

import static org.junit.Assert.*;

import java.awt.List;

import org.junit.Before;
import org.junit.Test;

import week1.collections.Queue;

public class QueueTest {
	Queue queue = null;
	@Before
	public void init(){
		queue = new Queue();
		for(int i=1;i<=10;i++){
			queue.enQueue(i);
		}
	}
	
	@Test
	public void test1(){
		for(int i=1;i<=10;i++){
			System.out.println(queue.size());
			assertEquals(queue.deQueue(), i);
		}
		assertEquals(queue.isEmpty(), true);
	}
	
}
