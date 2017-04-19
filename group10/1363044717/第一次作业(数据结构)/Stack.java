package com.coding.basic;

public class Stack<E> {

	private ArrayList<E> elementData = new ArrayList();
	public void push(E o){
		elementData.add(o);
	}

	/**
	 * 移除栈顶并返回他
	 * @return
	 */
	public E pop(){
		return elementData.remove(elementData.size()-1);
	}
	/**
	 * 得到栈顶元素
	 * @return
	 */
	public E peek(){
		return elementData.get(elementData.size()-1);
	}
	public boolean isEmpty(){
		return elementData.isEmpty();
	}
	public int size(){
		return elementData.size();
	}
}
