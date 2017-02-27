package com;

import java.util.Arrays;

/**
 * 数组实现队列
 * 
 * @Author xuyangyang
 * @Describe
 * @date 2017年2月23日
 */
public class MyQueueArray {

	private int size;// 大小
	private int head;// 头
	private Object[] elementData;// 存放队列的数组
	private int initCapacity = 10;// 初始容量

	/**
	 * 初始化队列容量10
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017年2月23日
	 */
	public MyQueueArray() {
		elementData = new Object[initCapacity];
	}

	/**
	 * 获取队列大小
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017年2月23日
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * 入队
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017年2月23日
	 * @param o
	 */
	public void enQueue(Object o) {
		if (size + 1 > elementData.length) {
			int oldLength = elementData.length;
			int newLength = oldLength + oldLength >> 1;
			elementData = Arrays.copyOf(elementData, newLength);
		}
		elementData[size++] = o;
	}

	/**
	 * 出队
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017年2月23日
	 * @return
	 */
	public Object deQueue() {
		if (size == 0) {
			throw new NullPointerException();
		}
		Object obj = elementData[head];
		elementData[head] = 0;
		head++;
		size--;
		return obj;
	}

	/**
	 * 测试
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017年2月23日
	 * @param args
	 */
	public static void main(String[] args) {
		MyQueueArray myQueue = new MyQueueArray();
		MyQueueLinked myQueueLinked = new MyQueueLinked();
		myQueue.enQueue("第1次入队");
		myQueue.enQueue("第2次入队");
		myQueue.enQueue("第3次入队");
		myQueue.enQueue("第4次入队");
		myQueue.enQueue("第5次入队");
		myQueue.enQueue("第6次入队");
		System.out.println(myQueue.size);

		System.out.println(myQueue.deQueue());
		System.out.println(myQueue.size);

		System.out.println(myQueue.deQueue());
		System.out.println(myQueue.size);

		System.out.println(myQueue.size);

	}

}
