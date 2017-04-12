package org.wsc.coding.basic.stack;

import org.wsc.coding.basic.list.ArrayList;

/**
 * 栈
 *
 * @author Administrator
 * @date 2017年4月7日下午3:06:06
 * @version v1.0
 *
 * @param <E>
 */
public class Stack<E> {
	private ArrayList<E> elementData = new ArrayList<E>();

	/**
	 * 将元素压入栈顶
	 * 
	 * @param e
	 */
	public void push(E e) {
		elementData.add(e);
	}

	/**
	 * 弹出栈顶元素
	 * 
	 * @return
	 */
	public E pop() {
		return elementData.remove(elementData.size() - 1);
	}

	/**
	 * 观察栈顶元素
	 * 
	 * @return
	 */
	public E peek() {
		return elementData.get(elementData.size() - 1);
	}

	/**
	 * 是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return elementData.isEmpty();
	}

	/**
	 * 栈深度
	 * 
	 * @return
	 */
	public int size() {
		return elementData.size();
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("[");
		for (int i = 0; i < elementData.size(); i++) {
			str.append(elementData.get(i));
			if(i < elementData.size()-1)
				str.append(", ");
		}
		str.append("]");
		return str.toString();
	}
	
	
}
