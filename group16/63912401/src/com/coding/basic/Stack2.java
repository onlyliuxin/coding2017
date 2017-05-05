package com.coding.basic;

/**
 * 栈数据结构
 * Stack
 * @author greenhills
 * 2017年2月25日 下午9:49:41
 */
public class Stack2 {
	private LinkedList elementData = new LinkedList();
	
	/**
	 * 入栈
	 * @param o
	 */
	public void push(Object o){	
		elementData.addFirst(o);
	}
	
	/**
	 * 出栈
	 * @return
	 */
	public Object pop(){
		return elementData.removeFirst();
	}
	
	/**
	 * 获取栈顶数据
	 * @return
	 */
	public Object peek(){
		return elementData.get(0);
	}
	
	/**
	 * 判断是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return elementData.size()==0;
	}
	
	/**
	 * 获取栈内数据量
	 * @return
	 */
	public int size(){
		return elementData.size();
	}
}
