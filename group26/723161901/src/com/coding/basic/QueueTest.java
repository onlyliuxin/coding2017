package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QueueTest {
	Queue queue = new Queue();
    @Before  
    public void before() throws Exception {  
		System.out.println("入列队");
    	int l = 3;
		System.out.println("长度："+l);
    	for(int i = 0; i < l; i++){
    		queue.enQueue(i);
    		System.out.println(i);
    	}
    } 	

	@Test
	public void test() {

	}
	
    @After  
    public void after(){  
		System.out.println("出列队");
		for (int i = 0 ;i < queue.size(); i++) {
			System.out.println(queue.deQueue());
		}
		System.out.println("长度："+queue.size());
		System.out.println("**************");		
    }  

}
