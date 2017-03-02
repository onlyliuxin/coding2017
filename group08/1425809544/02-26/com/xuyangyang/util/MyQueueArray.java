package com;

import java.util.Arrays;

/**
 * ����ʵ�ֶ���
 * 
 * @Author xuyangyang
 * @Describe
 * @date 2017��2��23��
 */
public class MyQueueArray {

	private int size;// ��С
	private int head;// ͷ
	private Object[] elementData;// ��Ŷ��е�����
	private int initCapacity = 10;// ��ʼ����

	/**
	 * ��ʼ����������10
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017��2��23��
	 */
	public MyQueueArray() {
		elementData = new Object[initCapacity];
	}

	/**
	 * ��ȡ���д�С
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017��2��23��
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * ���
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017��2��23��
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
	 * ����
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017��2��23��
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
	 * ����
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017��2��23��
	 * @param args
	 */
	public static void main(String[] args) {
		MyQueueArray myQueue = new MyQueueArray();
		MyQueueLinked myQueueLinked = new MyQueueLinked();
		myQueue.enQueue("��1�����");
		myQueue.enQueue("��2�����");
		myQueue.enQueue("��3�����");
		myQueue.enQueue("��4�����");
		myQueue.enQueue("��5�����");
		myQueue.enQueue("��6�����");
		System.out.println(myQueue.size);

		System.out.println(myQueue.deQueue());
		System.out.println(myQueue.size);

		System.out.println(myQueue.deQueue());
		System.out.println(myQueue.size);

		System.out.println(myQueue.size);

	}

}
