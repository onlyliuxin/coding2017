package com.coding.basic;

/**
 * 2017/2/24
 * @author 236995728
 *
 */
public class Queue {
	private ArrayList list = new ArrayList();
	/**
	 * 进队列
	 * @param o
	 */
	public void enQueue(Object o){		
		list.add(o);
	}
	
	/**
	 * 出队列
	 * @return
	 */
	public Object deQueue(){
		Object o = null;
		if(list.size() != 0){
			o = list.remove(0);
		}
		return o;
	}
	
	/**
	 * 队列是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return list.size() == 0;
	}
	
	/**
	 * 队列元素个数
	 * @return
	 */
	public int size(){
		return list.size();
	}
}
