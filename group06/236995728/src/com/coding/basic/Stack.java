package com.coding.basic;

/**
 * 2017/2/24
 * @author 236995728
 *
 */
public class Stack {
	private ArrayList elementData = new ArrayList();
	
	/**
	 * push进元素
	 * @param o
	 */
	public void push(Object o){	
		elementData.add(o);
	}

	/**
	 * pop出元素
	 * @return
	 */
	public Object pop(){
		Object o = elementData.get(elementData.size()-1);
		elementData.remove(elementData.size()-1);
		return o;
	}
	
	/**
	 * 查找栈顶元素
	 * @return
	 */
	public Object peek(){
		return elementData.get(elementData.size()-1);
	}
	
	/**
	 * 判断是否为空栈
	 * @return
	 */
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	
	/**
	 * 栈里元素的个数
	 * @return
	 */
	public int size(){
		return elementData.size();
	}
}
