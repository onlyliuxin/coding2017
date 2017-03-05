package com.coding.basic;

/**
 * Stack ʵ��
 * Last In First Out
 * ��14С�� 296933284
 * 
 * @author Tonnyson
 *
 */
public class Stack {
	
	private ArrayList elementData = new ArrayList();
	private int top = 0;

	/**
	 * ��ջ�в���Ԫ��
	 * 
	 * @param obj
	 */
	public void push(Object obj) {
		elementData.add(obj);
		top++;
	}
	
	/**
	 * ��ջ��ȡ��Ԫ��
	 * 
	 * @return
	 */
	public Object pop() {
		return elementData.remove(--top);
	}

	/**
	 * ��ȡջ��Ԫ��
	 * 
	 * @return
	 */
	public Object peek() {
		return elementData.get(top - 1);
	}
	
	/**
	 * �ж�ջ�Ƿ�Ϊ��
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return top == 0;
	}
	
	/**
	 * ��ȡջ��Ԫ�ظ���
	 * 
	 * @return
	 */
	public int size() {
		return top;
	}
}
