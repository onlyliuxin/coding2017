package com.coding.test;
import com.coding.datastructs.Queue;


public class QueueTest {

	/**
	 * <p>Description:</p>
	 * @param args
	 * @author:Wilson huang
	 * @date 2017-3-12обнГ2:42:15
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue q = new Queue();
		q.enQueue("a");
		q.enQueue("b");
		System.out.println(q.deQueue());

	}

}
