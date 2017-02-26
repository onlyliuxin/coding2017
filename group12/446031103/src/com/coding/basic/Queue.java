package com.coding.basic;

/**
 * 
 * 队列-先进先出
 * 
 * @ClassName Queue
 * @author msh
 * @date 2017年2月21日 下午9:29:03
 */
public class Queue {
	private LinkedList elementData = new LinkedList();
	/**
	 * 
	 * 入队列
	 *
	 * @MethodName enQueue
	 * @author msh
	 * @date 2017年2月21日 下午9:45:15
	 * @param o
	 */
	public void enQueue(Object o){		
		elementData.add(o);
	}
	/**
	 * 
	 * 离开队列
	 *
	 * @MethodName deQueue
	 * @author msh
	 * @date 2017年2月21日 下午9:56:06
	 * @return
	 */
	public Object deQueue(){
		if(isEmpty())
			throw new IndexOutOfBoundsException("size："+size());
		Object o=elementData.get(0);
		elementData.removeFirst();
		return o;
	}
	/**
	 * 
	 * 是否为空
	 *
	 * @MethodName isEmpty
	 * @author msh
	 * @date 2017年2月21日 下午9:57:14
	 * @return
	 */
	public boolean isEmpty(){
		boolean temp = false;
		if(0==elementData.size())
			temp= true;
		return temp;
	}
	/**
	 * 
	 * 队列中元素
	 *
	 * @MethodName size
	 * @author msh
	 * @date 2017年2月21日 下午9:57:28
	 * @return
	 */
	public int size(){
		return elementData.size();
	}
}
