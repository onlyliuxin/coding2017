package com.coding.basic;

/**
 * 栈数据结构
 * Stack
 * @author greenhills
 * 2017年2月25日 下午9:49:41
 */
public class Stack {
	private ArrayList elementData = new ArrayList();
	
	/**
	 * 入栈
	 * @param o
	 */
	public void push(Object o){	
		elementData.add(o);
	}
	
	/**
	 * 出栈
	 * @return
	 */
	public Object pop(){
		return elementData.removeLast();
	}
	
	/**
	 * 获取栈顶数据
	 * @return
	 */
	public Object peek(){
		return elementData.getLast();
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
