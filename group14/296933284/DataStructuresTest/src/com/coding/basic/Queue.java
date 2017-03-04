package com.coding.basic;

/**
 * Queue ʵ��
 * First In First Out
 * ��14С�� 296933284
 * 
 * @author Tonnyson
 *
 */
public class Queue {
	
	private LinkedList elementData = new LinkedList();
	
	/**
	 * ������в���Ԫ��
	 * 
	 * @param obj
	 */
	public void enQueue(Object obj){		
		elementData.addLast(obj);
	}
	
	/**
	 * ɾ������Ԫ��
	 * 
	 * @return
	 */
	public Object deQueue(){
		return elementData.removeFirst();
	}
	
	/**
	 * �ж϶����Ƿ�Ϊ��
	 * 
	 * @return
	 */
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	
	/**
	 * ���ض��е�Ԫ�ظ���
	 * 
	 * @return
	 */
	public int size(){
		return elementData.size();
	}
}
