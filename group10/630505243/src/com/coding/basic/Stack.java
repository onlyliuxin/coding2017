package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	private int size = 0;
	
	/**
	 * 把项压入堆栈顶部
	 * @param o
	 */
	public void push(Object o){		
		elementData.add(size,o);
		size++;
	}
	
	/**
	 * 移除堆栈顶部的对象，并作为此函数的值，返回该对象
	 * @return
	 */
	public Object pop(){
		if(this.elementData.size()>0){
			Object o = elementData.get(size-1);
			elementData.remove(size);
			size--;
			return o;
		}else{
			return null;
		}
	}
	
	/**
	 * 查看堆栈顶部的对象，但不从堆栈中移除它
	 * @return
	 */
	public Object peek(){
		if(size>0){
			return elementData.get(size-1);
		}else{
			return null;
		}
	}
	
	/**
	 * 测试堆栈是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return size>0?false:true;
	}
	
	/**
	 * 获取堆栈大小
	 * @return
	 */
	public int size(){
		return this.size;
	}
}
