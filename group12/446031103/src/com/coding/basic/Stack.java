package com.coding.basic;

/**
 * 
 * 栈-先进后出
 * 
 * @ClassName Stack
 * @author msh
 * @date 2017年2月21日 下午9:05:39
 */
public class Stack {
	
	private ArrayList elementData = new ArrayList();
	/**
	 * 
	 * 向栈中加入元素
	 *
	 * @MethodName push
	 * @author msh
	 * @date 2017年2月21日 下午9:12:03
	 * @param o
	 */
	public void push(Object o){		
		elementData.add(o);
	}
	/**
	 * 
	 * 从栈中取出元素
	 *
	 * @MethodName pop
	 * @author msh
	 * @date 2017年2月21日 下午9:12:51
	 * @return
	 */
	public Object pop(){	
		Object o= peek();
		elementData.remove(size()-1);
		return o;
	}
	/**
	 * 
	 * 取出栈顶元素
	 *
	 * @MethodName peek
	 * @author msh
	 * @date 2017年2月21日 下午9:13:08
	 * @return
	 */
	public Object peek(){
		Object o=elementData.get(size()-1);		
		return o;
	}
	/**
	 * 
	 * 判断栈中是否有元素
	 *
	 * @MethodName isEmpty
	 * @author msh
	 * @date 2017年2月21日 下午9:14:26
	 * @return
	 */
	public boolean isEmpty(){
		boolean temp = false;
		if(0==size())
			temp = true;
		return temp;
	}
	/**
	 * 
	 * 栈中有多少元素
	 *
	 * @MethodName size
	 * @author msh
	 * @date 2017年2月21日 下午9:16:42
	 * @return
	 */
	public int size(){
		return elementData.size();
	}
	
}
